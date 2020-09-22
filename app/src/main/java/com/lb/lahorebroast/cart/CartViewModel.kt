package com.lb.lahorebroast.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lb.lahorebroast.networking.APIClient
import com.lb.lahorebroast.model.Cities
import com.lb.lahorebroast.model.OrderResponse
import com.lb.lahorebroast.networking.RequestService
import com.lb.lahorebroast.utilities.AppDatabase
import com.lb.lahorebroast.utilities.CacheManager

class CartViewModel(application: Application) : AndroidViewModel(application) {
    private var allCart : LiveData<List<Cart>>? = null
    private var repository : Repository
    private var locationList : MutableLiveData<ArrayList<Cities>> = MutableLiveData<ArrayList<Cities>>()
    private var retrofit = APIClient.retrofitInstance.create(RequestService::class.java)
    public lateinit var serverResponse :ServerResponse

    init {

        val db = AppDatabase(application)
        repository = Repository.getInstance(db.todoDao())
        allCart= getAllCart()
        locationList.value = CacheManager.instance.getLocationsData()
    }

     fun getLocations(): MutableLiveData<ArrayList<Cities>> {

        return locationList
    }

    fun getAllCart():LiveData<List<Cart>> = repository.getAllUsers()

    fun insertCart(cart: Cart) = repository.insertUser(cart)

    fun getProductByID(id : Int ) = repository.getByID(id)

    fun updateCart(id : Int , quantity : Int , total : Double) = repository.updateByID(id,quantity,total)

    fun deleteCart(cart: Cart) = repository.deleteCart(cart)

    fun deleteAll() = repository.deleteAll()

    fun submitOrder(order : HashMap<String,Any> )
    {
        val call : retrofit2.Call<OrderResponse> = retrofit.createOrders(order)
        call.enqueue(object : retrofit2.Callback<OrderResponse>
        {
            override fun onFailure(call: retrofit2.Call<OrderResponse>?, t: Throwable?) {

                            }

            override fun onResponse(
                call: retrofit2.Call<OrderResponse>?,
                response: retrofit2.Response<OrderResponse>?
            ) {
                if(null != response?.body())
                {
                    if(response.body().status!!)
                        {
                            serverResponse.onOrderSubmitted(true)
                        }else
                    {
                        serverResponse.onOrderSubmitted(false)
                    }
                }else
                {
                    serverResponse.onOrderSubmitted(false)
                }

            }

        })
    }

    interface ServerResponse
    {
        fun onOrderSubmitted(boolean: Boolean)
    }
}