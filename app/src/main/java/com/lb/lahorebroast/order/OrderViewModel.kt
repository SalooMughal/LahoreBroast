package com.lb.lahorebroast.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lb.lahorebroast.networking.APIClient
import com.lb.lahorebroast.model.OrderResponseGet
import com.lb.lahorebroast.networking.RequestService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderViewModel : ViewModel() {
    private var retrofit = APIClient.retrofitInstance.create(RequestService::class.java)
    private var orderLiveList: MutableLiveData<ArrayList<OrderResponseGet.Data>> = MutableLiveData()
    private var orderList: ArrayList<OrderResponseGet.Data> = ArrayList()
    lateinit var serverResponse: OnServerResponse


    fun getOrders(id : Int )
    {
        val call : Call<OrderResponseGet> = retrofit.getOrder(id)
        call.enqueue(object : Callback<OrderResponseGet>
        {
            override fun onFailure(call: Call<OrderResponseGet>?, t: Throwable?) {
                serverResponse.onFailure()
            }

            override fun onResponse(
                call: Call<OrderResponseGet>?,
                response: Response<OrderResponseGet>?
            ) {
                if(null != response?.body())
                {
                    if(response.body().data!!.isNotEmpty())
                    {
                        orderList.clear()
                        orderList.addAll(response.body().data!!)
                        orderLiveList.value = orderList
                        serverResponse.onSuccess()
                    }else
                    {
                        serverResponse.onFailure()
                    }
                }else
                {
                    serverResponse.onFailure()
                }
            }

        })
    }
    fun getOrders(): MutableLiveData<ArrayList<OrderResponseGet.Data>> {

        orderLiveList.value = orderList
        return orderLiveList
    }
    interface OnServerResponse
    {
        fun onSuccess()
        fun onFailure()
    }
}