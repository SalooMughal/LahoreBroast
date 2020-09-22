package com.lb.lahorebroast.order

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.lb.lahorebroast.R
import com.lb.lahorebroast.adapters.OrderItemsAdapter
import com.lb.lahorebroast.model.OrderResponseGet
import com.lb.lahorebroast.model.SingleOrder
import kotlinx.android.synthetic.main.order_details_fragment.*
import kotlinx.android.synthetic.main.single_order_item.view.*
import kotlinx.android.synthetic.main.single_order_item.view.orderDate
import kotlinx.android.synthetic.main.single_order_item.view.orderDay
import kotlinx.android.synthetic.main.single_order_item.view.orderItemQty
import kotlinx.android.synthetic.main.single_order_item.view.orderTotal
import kotlinx.android.synthetic.main.single_order_item.view.orderType
import org.json.JSONObject


class OrderDetails(val order: OrderResponseGet.Data) : Fragment() {

    private lateinit var orderItemAdapter: OrderItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.order_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bckOrderDetailPage.setOnClickListener { activity!!.supportFragmentManager.popBackStack() }
        initializeView()

    }

    fun initializeView()
    {
        orderItemAdapter = OrderItemsAdapter()
        rvOrderItems.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = orderItemAdapter
        }
        orderTotal.text = order.cart_total.toString()
        orderItemQty.text = order.cart_qty.toString()
        orderDate.text =order.createdAt

        orderDay.text = order.status!!
        if(null != order.completeJson)
        { val list = ArrayList<SingleOrder>()
            val items = JSONObject(order.completeJson)
            if(items.has("orders"))
            {
                val innerObj = items.getJSONObject("orders")
                innerObj.keys().forEach {
                    it ->
                    list.add(Gson().fromJson(innerObj.get(it).toString(),SingleOrder::class.java))
                    }
            }

            if(list.isNotEmpty())
            {
                orderItemAdapter.submitList(list)
            }
        }


    }
}