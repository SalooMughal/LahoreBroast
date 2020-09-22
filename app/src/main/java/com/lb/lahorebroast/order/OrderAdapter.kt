package com.lb.lahorebroast.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.room.FtsOptions
import com.lb.lahorebroast.R
import com.lb.lahorebroast.model.OrderResponseGet
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.single_order_item.view.*
import org.json.JSONObject

class OrderAdapter(val onItemClick :(pos : Int) -> Unit ) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    private var arrayList : ArrayList<OrderResponseGet.Data> = ArrayList()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = arrayList[position]
        holder.type.text = "Cash on Delivery"
        holder.orderQty.text = list.cart_qty.toString()
        holder.total.text = list.cart_total.toString()
        holder.orderDate.text = list.createdAt

        holder.orderDay.text = list.status
        holder.orderItem.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_order_item, parent, false))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun submitList(productList : ArrayList<OrderResponseGet.Data>) {
        arrayList = productList
        notifyDataSetChanged()
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val type: TextView = view.orderType
        val total: TextView = view.orderTotal
        val orderQty: TextView= view.orderItemQty
        val orderDate: TextView = view.orderDate
        val orderDay: TextView = view.orderDay
        val orderItem: ConstraintLayout = view.orderItem
    }

    fun getStatus(status : Int):String
    {
        when(status)
        {
            1 -> return "pending"
            2 -> return "out from store"
            3 -> return "delivered"
        }
        return ""
    }
}