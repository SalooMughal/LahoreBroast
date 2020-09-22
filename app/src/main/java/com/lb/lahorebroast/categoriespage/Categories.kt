package com.lb.lahorebroast.categoriespage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lb.lahorebroast.R
import com.lb.lahorebroast.adapters.CategoriesAdapter
import com.lb.lahorebroast.adapters.ProductAdapter
import com.lb.lahorebroast.model.Category
import com.lb.lahorebroast.model.Products
import com.lb.lahorebroast.productDetail.DetailItemFragment
import kotlinx.android.synthetic.main.fragment_categories.*


class Categories : Fragment() {

    private lateinit var viewModel: CategoriesViewModel
    private lateinit var productAdapter: ProductAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        productAdapter = ProductAdapter (::selectProduct,0)
        categoriesAdapter = CategoriesAdapter (::selectCategory)
        categoryRecycler.apply {
            layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.HORIZONTAL,false)
            adapter = categoriesAdapter
        }
        productRecycler.apply {
            val count = 2
            layoutManager = GridLayoutManager(activity,count, GridLayoutManager.VERTICAL,false)
            adapter = productAdapter
        }

        viewModel.getFilterProducts().observe(viewLifecycleOwner, Observer<ArrayList<Products>> {
            val animation1 = AlphaAnimation(0.2f, 1.0f)
            animation1.duration = 200
            animation1.startOffset = 200
            animation1.fillAfter = true
            productRecycler.startAnimation(animation1)
            productAdapter.submitList(it)
        })

        viewModel.getCategories().observe(viewLifecycleOwner, Observer<ArrayList<Category>> {
            categoriesAdapter.submitList(it)
            if(it.isNotEmpty()) {
                if(null != it[0].name) {
                    viewModel.filterList(it[0].name!!)
                }
            }
        })
    }

    private fun selectProduct(position : Int)
    {
        val ft = activity!!.supportFragmentManager.beginTransaction()
        ft.add(R.id.frame_home_main,
            DetailItemFragment(viewModel.getFilterProducts().value?.get(position)!!)
        ).addToBackStack(null).commit()
    }
    private fun selectCategory(position : Int)
    {
        categoriesAdapter.selectItemView(position)
        val cat = viewModel.getCategories().value?.get(position)
            if(null != cat?.name) {
                viewModel.filterList(cat.name)
            }
    }
}