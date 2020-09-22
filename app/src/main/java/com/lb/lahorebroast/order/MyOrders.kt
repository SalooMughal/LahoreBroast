package com.lb.lahorebroast.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lb.lahorebroast.R
import com.lb.lahorebroast.cart.CartViewModel
import com.lb.lahorebroast.model.OrderResponseGet
import com.lb.lahorebroast.utilities.CacheManager
import com.lb.lahorebroast.utilities.Utilities
import kotlinx.android.synthetic.main.fragment_my_orders.*

class MyOrders : Fragment(),OrderViewModel.OnServerResponse {

    private lateinit var viewModel: OrderViewModel
    private lateinit var orderAdapter: OrderAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_orders, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        viewModel.serverResponse = this
        orderAdapter = OrderAdapter(::onItemClick)
        Utilities.progressdialog(activity!!)
        viewModel.getOrders(CacheManager.instance.getUserData().id!!)
        myOrdersBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        rvOrder.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = orderAdapter
        }
        viewModel.getOrders().observe(viewLifecycleOwner, object : Observer<ArrayList<OrderResponseGet.Data>>
        {
            override fun onChanged(t: ArrayList<OrderResponseGet.Data>?) {
                t?.let {
                    orderAdapter.submitList(it)
                }
            }

        })
    }
    private fun onItemClick(pos: Int) {
        val order =  viewModel.getOrders().value?.get(pos)
        val fm = activity!!.supportFragmentManager.beginTransaction()
        fm.add(android.R.id.content, OrderDetails(order!!)).addToBackStack(null).commit()
    }
    override fun onSuccess() {
        Utilities.finishprogress()
    }

    override fun onFailure() {
        Utilities.finishprogress()
    }

}