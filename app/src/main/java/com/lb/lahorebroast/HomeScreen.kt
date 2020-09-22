package com.lb.lahorebroast

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.lb.lahorebroast.adapters.ProductAdapter
import com.lb.lahorebroast.model.Products
import com.lb.lahorebroast.model.Promotions
import com.lb.lahorebroast.productDetail.DetailItemFragment
import com.lb.lahorebroast.utilities.CustomPagerAdapter
import kotlinx.android.synthetic.main.home_screen_fragment.*
import java.util.*
import kotlin.collections.ArrayList

class HomeScreen : Fragment() {
    private lateinit var productAdapter: ProductAdapter
    private lateinit var bannerAdapter: CustomPagerAdapter
    private lateinit var viewModel: HomeScreenViewModel
    private var timer: Timer? = null
    private var page = 0
    private var maxPages = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_screen_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeScreenViewModel::class.java)
        productAdapter = ProductAdapter(::selectItem,1)
        bannerAdapter = CustomPagerAdapter(activity!!)
        homeRecycler.apply {
            val count = 3
            layoutManager = GridLayoutManager(activity,count, GridLayoutManager.VERTICAL,false)
            adapter = productAdapter
        }
        viewModel.getProducts().observe(viewLifecycleOwner, Observer<ArrayList<Products>> {
            productAdapter.submitList(it)
        })
            pageIndicator.visibility = View.VISIBLE
            bannerImage.apply {
                adapter = bannerAdapter
            }
            viewModel.getSliders()
                .observe(viewLifecycleOwner, Observer<ArrayList<Promotions.Datum>> {
                    bannerAdapter.setData(it)
                    pageIndicator.addDots(bannerImage,it.size)
                    maxPages = it.size
                    pageSwitcher(5)
                })

    }
    fun pageSwitcher(seconds: Int) {
        timer = Timer()
        timer!!.scheduleAtFixedRate(RemindTask(), 0, seconds * 1000.toLong())
    }
    private fun selectItem(position : Int)
    {
        val ft = activity!!.supportFragmentManager.beginTransaction()
        ft.replace(R.id.frame_home_main,DetailItemFragment(viewModel.getProducts().value?.get(position)!!)).addToBackStack(null).commit()
    }

    inner class RemindTask: TimerTask(){

        override fun run() {
            if(null != activity) {
                activity!!.runOnUiThread {
                    if (page == maxPages) {
                        page = 0
                    } else {
                        if(null != bannerImage) {
                            bannerImage.setCurrentItem(page++, true)
                        }
                    }
                }
            }
        }
    }

}