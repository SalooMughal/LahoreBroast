package com.lb.lahorebroast.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lb.lahorebroast.R

import com.lb.lahorebroast.cart.Cart
import com.lb.lahorebroast.utilities.CacheManager
import com.lb.lahorebroast.utilities.ImageWidget
import kotlinx.android.synthetic.main.cart_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class CartAdapter(val addToCart: (position: Int, count : Int) -> Unit,val subFromCart: (position: Int , count : Int) -> Unit) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    private var arrayList : ArrayList<Cart> = ArrayList()
    private var countList : ArrayList<Int> = ArrayList()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = arrayList[position]
            holder.title.text = list.product_name
            holder.unit.text = list.product_unit
        holder.image.loadImageFromUrl(list.product_image)
        holder.total.text = list.product_total.toString()
        holder.productQty.text = list.product_qty.toString()
        holder.minBtn.setOnClickListener{
            var count = list.product_qty
            if(count>=1) { count -= 1
            holder.productQty.text = (count).toString()
            subFromCart(position,count) }
        }
        holder.maxBtn.setOnClickListener{
            var count = list.product_qty
            count += 1
            holder.productQty.text = (count).toString()
            addToCart(position,count)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun submitList(productList : ArrayList<Cart>) {
        arrayList = productList
        notifyDataSetChanged()
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.cartProTitle
        val total: TextView = view.cartProTotal
        val image: ImageWidget = view.cartImg
        val unit: TextView = view.cartProUnit
        val minBtn: ImageView = view.carItemMinus
        val maxBtn: ImageView = view.cartItemPlus
        val productQty: TextView = view.cartItemCount

    }

}