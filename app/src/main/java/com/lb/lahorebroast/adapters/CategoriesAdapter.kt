package com.lb.lahorebroast.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.lb.lahorebroast.R
import com.lb.lahorebroast.model.Category
import kotlinx.android.synthetic.main.categoryitem.view.*


class CategoriesAdapter(var selectItem: (position: Int) -> Unit) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    private var arrayList : ArrayList<Category> = ArrayList()
    private var selectedItem = 0
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = arrayList[position]
        if(position == selectedItem)
        {
            holder.bottomLine.visibility = View.VISIBLE
            holder.title.textSize = 20f
        }else
        {
            holder.bottomLine.visibility = View.GONE
            holder.title.textSize = 14f
        }

        holder.title.text = list.name
        holder.mainView.setOnClickListener {
            selectItem(position) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.categoryitem, parent, false))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun submitList(categoryList : ArrayList<Category>) {
        arrayList = categoryList
        notifyDataSetChanged()
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.catTitle
        val bottomLine : View = view.selectedItemLine
        val mainView: ConstraintLayout = view.mainView

    }

     fun selectItemView(position: Int)
    {
        selectedItem = position
        notifyDataSetChanged()
    }

}