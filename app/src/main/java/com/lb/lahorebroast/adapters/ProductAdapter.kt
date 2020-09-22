package com.lb.lahorebroast.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.lb.lahorebroast.R
import com.lb.lahorebroast.model.Products
import com.lb.lahorebroast.utilities.ImageWidget
import kotlinx.android.synthetic.main.product_item.view.*


class ProductAdapter(val selectItem: (position: Int) -> Unit , val type : Int) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private var arrayList : ArrayList<Products> = ArrayList()
    private var countList : ArrayList<Int> = ArrayList()
    private var count = 1
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = arrayList[position]
        holder.title.text = list.name
        holder.unit.text = list.unit
        holder.image.loadImageFromUrl(list?.image)
        holder.price.text = ("PkR ${list.sellPriceIncTax!!.substringBefore(".00")}")
        holder.productItem.setOnClickListener{
            selectItem(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(type == 1) {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
            )
        }else
        {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.product_item2, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun submitList(productList : ArrayList<Products>) {
        arrayList = productList
        notifyDataSetChanged()
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.productTitle
        val price: TextView = view.productPrice
        val image: ImageWidget = view.productImg
        val unit: TextView = view.productUnit
        val productItem: ConstraintLayout = view.productItem
    }

}