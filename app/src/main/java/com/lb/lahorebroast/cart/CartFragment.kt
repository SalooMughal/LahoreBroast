package com.lb.lahorebroast.cart

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jaredrummler.materialspinner.MaterialSpinner
import com.lb.lahorebroast.LoginSignupActivity
import com.lb.lahorebroast.MainActivity
import com.lb.lahorebroast.R
import com.lb.lahorebroast.adapters.CartAdapter
import com.lb.lahorebroast.model.Area
import com.lb.lahorebroast.model.Branch
import com.lb.lahorebroast.model.Cities
import com.lb.lahorebroast.utilities.CacheManager
import com.lb.lahorebroast.utilities.Utilities
import kotlinx.android.synthetic.main.bottom_cart_layout.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CartFragment : Fragment(), CartViewModel.ServerResponse {

    private var is_discount = false
    private lateinit var couponResponse: CouponResponse
    private var totalAmount = 0.0
    private var count = 0
    private var noDiscountAmnt = 0.0
    private var discountAmnt = 0.0
    private lateinit var locationObjects: ArrayList<Cities>
    private lateinit var branchObjects: ArrayList<Branch>
    private lateinit var areaObjects: ArrayList<Area>
    private lateinit var locationList: ArrayList<String>
    private lateinit var branchList: ArrayList<String>
    private lateinit var areaList: ArrayList<String>
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var cartAdapter: CartAdapter
    private lateinit var viewModel: CartViewModel
    private var cartList = ArrayList<Cart>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fillAddress()
        locationObjects = ArrayList()
        locationList = ArrayList()
        branchList = ArrayList()
        areaList = ArrayList()
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        viewModel.serverResponse = this
        cartAdapter = CartAdapter(::addToCart, ::subFromCart)
        rvCart.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = cartAdapter
        }
        viewModel.getAllCart().observe(viewLifecycleOwner,
            Observer<List<Cart>> { t ->
                cartAdapter.submitList(t as ArrayList<Cart>)
                cartList = t
                count = 0
                noDiscountAmnt = 0.0
                discountAmnt = 0.0
                totalAmount = 0.0
                cartList.forEach {
                    count += it.product_qty
                    totalAmount += it.product_total
                    if (it.car_id == "Deals") {
                        noDiscountAmnt += it.product_total
                    } else {
                        discountAmnt += it.product_total
                    }
                }
                cartBottomQty.text = count.toString()
                if(is_discount)
                {
                    val coupon: Int = (couponResponse.data?.discountAmount)!!.toInt()
                    if (couponResponse.data.discountType.equals("fixed")) {
                        if (discountAmnt > 0.0) {
                            val discountedAmount = discountAmnt.minus(coupon)
                            cartButtomAmount.text = String.format("%.2f", discountedAmount + noDiscountAmnt)
                            discount.text = coupon.toString()
                        }
                    } else {
                        if (discountAmnt > 0.0) {
                            val discountedAmount = discountAmnt.minus((discountAmnt.times(coupon)).div(100))
                            cartButtomAmount.text = String.format("%.2f", discountedAmount + noDiscountAmnt)
                            discount.text = (discountAmnt.times(coupon)).div(100).toString()
                        }
                    }
                }else {
                    cartButtomAmount.text = String.format("%.2f", noDiscountAmnt + discountAmnt)
                }
                if (cartList.isEmpty()) {
                    clearAll.visibility = GONE
                } else {
                    clearAll.visibility = VISIBLE
                }
            })
        viewModel.getLocations().observe(viewLifecycleOwner,
            Observer<java.util.ArrayList<Cities>> { t ->
                locationObjects.addAll(t)
                locationObjects.forEach { it -> locationList.add(it.name!!) }
                cityMenu.setItems(locationList)
            })
        cityMenu.setOnItemSelectedListener(object : MaterialSpinner.OnItemSelectedListener<String> {
            override fun onItemSelected(
                view: MaterialSpinner?,
                position: Int,
                id: Long,
                item: String?
            ) {
                cityText.text = item
                branchList.clear()
                areaList.clear()
                branchText.text = ""
                areaText.text = ""
                branchMenu.setItems(branchList)
                areaMenu.setItems(areaList)
                branchObjects = locationObjects[position].branches as ArrayList<Branch>
                branchObjects.forEach { it ->
                    branchList.add(it.branchName!!)
                }
                branchMenu.setItems(branchList)
            }

        })

        branchMenu.setOnItemSelectedListener(object :
            MaterialSpinner.OnItemSelectedListener<String> {
            override fun onItemSelected(
                view: MaterialSpinner?,
                position: Int,
                id: Long,
                item: String?
            ) {
                branchText.text = item
                areaList.clear()
                areaMenu.setItems(areaList)
                areaText.text = ""
                areaObjects = branchObjects[position].areas as ArrayList<Area>
                areaObjects.forEach { it ->
                    areaList.add(it.areaName!!)
                }
                areaMenu.setItems(areaList)
            }

        })

        areaMenu.setOnItemSelectedListener(object : MaterialSpinner.OnItemSelectedListener<String> {
            override fun onItemSelected(
                view: MaterialSpinner?,
                position: Int,
                id: Long,
                item: String?
            ) {
                areaText.text = item
            }

        })

        couponCode.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (cartList.size > 0) {
                    if (s?.length == 8) {
                        is_discount = true
                        Utilities.progressdialog(activity!!)
                        viewModel.fetchCouponDetails(s.toString())
                    } else {
                        is_discount = false
                        discount.text = "0"
                        couponResponse = CouponResponse()
                        cartButtomAmount.text = String.format("%.2f", discountAmnt+noDiscountAmnt)
                    }
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        clearAll.setOnClickListener { showClearCartDialog() }
        instantOrder.setOnClickListener {
            if (Utilities.isUserLogin(activity!!)) {
                if (totalAmount <= (CacheManager.instance.getAppConfig().data.maxOrder).toInt() && totalAmount >= (CacheManager.instance.getAppConfig().data.minOrder).toInt()) {
                    if (areaText.text.isNotEmpty() && branchText.text.isNotEmpty() &&
                        cityText.text.isNotEmpty() && instantAddress.text.isNotEmpty() && cartList.isNotEmpty()
                    ) {
                        Utilities.progressdialog(activity!!)
                        val user = CacheManager.instance.getUserData()
                        val orderParam = HashMap<String, Any>()
                        orderParam["customer_id"] = user.id!!
                        orderParam["instructions"] = instantNotes.text.toString()
                        orderParam["date"] = Utilities.getCurrentDate()
                        orderParam["orders"] = createOrderItemObjects()
                        orderParam["addresses"] = instantAddress.text.toString()
                        orderParam["city"] = locationObjects[cityMenu.selectedIndex].id!!
                        orderParam["branch"] = branchObjects[branchMenu.selectedIndex].id!!
                        orderParam["area"] = areaObjects[areaMenu.selectedIndex].id!!
                        orderParam["cart_total"] = noDiscountAmnt + discountAmnt
                        orderParam["cart_qty"] = count
                        if (is_discount) {
                            orderParam["coupon_code"] = couponResponse.data.couponCode
                        } else {
                            orderParam["coupon_code"] = ""
                        }
                        orderParam["order_source"] = "mobile"
                        viewModel.submitOrder(orderParam)
                    } else {
                        Toast.makeText(
                            activity!!,
                            "Please fill the form to create your order",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        activity!!,
                        "Order amount can not exceed " + CacheManager.instance.getAppConfig().data.maxOrder + " and less than " + CacheManager.instance.getAppConfig().data.minOrder,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } else {
                startActivity(Intent(activity!!, LoginSignupActivity::class.java))
            }
        }
        addMoreItems.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("action", "addItems")
            startActivity(intent)
            activity!!.finish()
        }
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.peekHeight = 250
        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    arrow.setImageResource(R.drawable.down)

                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    arrow.setImageResource(R.drawable.arrow)
                }
            }

            override fun onSlide(
                bottomSheet: View,
                slideOffset: Float
            ) {
            }
        })

        arrow.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }

    private fun fillAddress() {
        if (CacheManager.instance.getIsLogin()) {
            instantAddress.setText(CacheManager.instance.getUserData().shipping_address)
        }
    }

    private fun showClearCartDialog() {
        MaterialAlertDialogBuilder(activity, R.style.AlertDialogTheme)
            .setTitle("Clear")
            .setMessage("Clearing cart will remove all items from cart")
            .setNeutralButton("cancel") { dialog, which ->
                // Respond to neutral button press
            }

            .setPositiveButton("Clear Cart") { dialog, which ->
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.deleteAll()

                }

            }
            .show()
    }

    private fun addToCart(pos: Int, count: Int) {
        addToCart2(pos, count)
    }

    private fun subFromCart(pos: Int, count: Int) {
        addToCart2(pos, count)
    }

    private fun addToCart2(position: Int, count: Int) {
        val cart = cartList[position]
        if (count == 0) {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.deleteCart(
                    cart
                )

            }
        } else {
            val newTotal = cart.product_price.times(count)
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.updateCart(
                    cart.product_id, count,
                    newTotal
                )

            }
        }
    }

    private fun generateSuccessDialog() {
        val dialog = Dialog(activity!!, R.style.AlertDialogTheme)
        dialog.setContentView(R.layout.success_dialog)
        val checkOrders = dialog.findViewById<Button>(R.id.proceedShopping)
        val goToHome = dialog.findViewById<Button>(R.id.goToHome)
        checkOrders.setOnClickListener {
            dialog.dismiss()
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("action", "order")
            startActivity(intent)
            activity!!.finish()
        }
        goToHome.setOnClickListener {
            dialog.dismiss()
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity!!.finish()
        }
        dialog.setCancelable(false)
        dialog.show()

    }

    private fun createOrderItemObjects(): HashMap<String, Any> {
        val items = HashMap<String, Any>()
        if (cartList.isNotEmpty()) {
            cartList.forEach { it ->
                val itemsData = HashMap<String, Any>()
                itemsData["unit_price"] = it.product_price
                itemsData["unit_price_inc_tax"] = it.product_price
                itemsData["product_id"] = it.id!!
                itemsData["product_name"] = it.product_name
                itemsData["quantity"] = it.product_qty
                itemsData["total"] = it.product_total
                itemsData["tax"] = 0
                itemsData["variation_id"] = it.variation_id
                items[it.variation_id.toString()] = itemsData
            }
        }
        return items
    }

    override fun onOrderSubmitted(boolean: Boolean, message: String) {
        if (boolean) {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.deleteAll()
            }
            generateSuccessDialog()
        } else {
            Utilities.finishprogress()
            Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCouponFailure(boolean: Boolean) {
        Toast.makeText(activity, "Coupon not found", Toast.LENGTH_SHORT).show()
        Utilities.finishprogress()
    }

    override fun onCouponSuccess(couponResponse: CouponResponse) {


        Toast.makeText(activity, "Coupon added successfully", Toast.LENGTH_SHORT).show()
        this.couponResponse = couponResponse
        val coupon: Int = (couponResponse.data?.discountAmount)!!.toInt()
        coupon.let {
            if (couponResponse.data.discountType.equals("fixed")) {
                if (discountAmnt > 0.0) {
                    val discountedAmount = discountAmnt.minus(coupon)
                    cartButtomAmount.text = String.format("%.2f", discountedAmount + noDiscountAmnt)
                    discount.text = coupon.toString()
                }
            } else {
                if (discountAmnt > 0.0) {
                    val discountedAmount = discountAmnt.minus((discountAmnt.times(coupon)).div(100))
                    cartButtomAmount.text = String.format("%.2f", discountedAmount + noDiscountAmnt)
                    discount.text = (discountAmnt.times(coupon)).div(100).toString()
                }
            }
            is_discount = true

        }

        Utilities.finishprogress()
    }

}