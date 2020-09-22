package com.lb.lahorebroast.productDetail

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lb.lahorebroast.MainActivity
import com.lb.lahorebroast.R
import com.lb.lahorebroast.cart.Cart
import com.lb.lahorebroast.model.Products
import kotlinx.android.synthetic.main.fragment_detailment_item_fragement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailItemFragment(val products: Products) : Fragment() {

    private var count = 1
    private var totalPrice = 0.0
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detailment_item_fragement, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        bckDetailPage.setOnClickListener {
            activity!!.supportFragmentManager.popBackStack()
        }
        productTitle2.text = products.name
        proImage.loadImageFromUrl(products.image)
        detailUnit.text = products.unit
        detailPrice.text = products.sellPriceIncTax!!.substringBefore(".00")
        detailTotal.text = products.sellPriceIncTax!!.substringBefore(".00")
        detailDes.text = Html.fromHtml(products.productDescription)
        detailCount.text = count.toString()
        detailPlus.setOnClickListener {
            count += 1
            totalPrice = (products.sellPriceIncTax!!.toDouble() * count).toDouble()
            detailCount.text = count.toString()
            detailTotal.text = totalPrice.toString()
        }
        detailMin.setOnClickListener {
            if (count > 1) {
                count -= 1
                totalPrice = (products.sellPriceIncTax!!.toDouble() * count).toDouble()
                detailCount.text = count.toString()
                detailTotal.text = totalPrice.toString()
            }
        }
        addToCart.setOnClickListener {
            val total = products.sellPriceIncTax!!.toDouble().times(count)
            val cart = Cart(
                null,
                products.id!!,
                products.variation_id!!,
                products.name!!,
                products.sellPriceIncTax!!.toDouble(),
                count,
                products.unit!!,
                total,
                products.image!!
            )
            CoroutineScope(Dispatchers.IO).launch {
                val insertedProduct = viewModel.getProductByID(products.id!!)
                if (null != insertedProduct) {
                    viewModel.updateCart(
                        insertedProduct.product_id, insertedProduct.product_qty + count,
                        insertedProduct.product_total + total
                    )
                } else {
                    viewModel.insertCart(cart)
                }
                this.launch(Dispatchers.Main)
                {
                    delay(100)
                    Toast.makeText(activity!!, "Item added to Cart", Toast.LENGTH_SHORT).show()
                    val intent = Intent(activity, MainActivity::class.java)
                    intent.putExtra("action","addItems")
                    startActivity(intent)
                    activity!!.finish()
                }
            }
        }
    }
}