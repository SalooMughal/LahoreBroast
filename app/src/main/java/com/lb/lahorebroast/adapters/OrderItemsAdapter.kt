package com.lb.lahorebroast.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.lb.lahorebroast.R
import com.lb.lahorebroast.model.SingleOrder
import com.lb.lahorebroast.utilities.ImageWidget
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.cart_item.view.cartImg
import kotlinx.android.synthetic.main.cart_item.view.cartItemCount
import kotlinx.android.synthetic.main.cart_item.view.cartProTitle
import kotlinx.android.synthetic.main.cart_item.view.cartProTotal
import kotlinx.android.synthetic.main.cart_item.view.cartProUnit
import kotlinx.android.synthetic.main.cart_item_order.view.*

class OrderItemsAdapter() : RecyclerView.Adapter<OrderItemsAdapter.ViewHolder>() {
    private var arrayList : ArrayList<SingleOrder> = ArrayList()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = arrayList[position]
        holder.title.text = list.productName
        holder.total.text = list.total.toString()
        holder.productQty.text = list.quantity.toString()
        holder.itemPrice.text = list.unitPrice.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cart_item_order, parent, false))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun submitList(productList : ArrayList<SingleOrder>) {
        arrayList = productList
        notifyDataSetChanged()
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.cartProTitle
        val total: TextView = view.cartProTotal
        val productQty: TextView = view.cartItemCount
        val itemPrice: TextView = view.singleItemPrice

    }

}