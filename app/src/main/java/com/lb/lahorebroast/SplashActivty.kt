package com.lb.lahorebroast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lb.lahorebroast.networking.APIClient

import com.lb.lahorebroast.model.*
import com.lb.lahorebroast.networking.RequestService
import com.lb.lahorebroast.utilities.CacheManager
import com.lb.lahorebroast.utilities.TinyDB
import com.lb.lahorebroast.utilities.Utilities
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivty : AppCompatActivity() {
    private var retrofit = APIClient.retrofitInstance.create(RequestService::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_activty)
        if(Utilities.isUserLogin(this))
        {
            CacheManager.instance.setUserData(TinyDB(this).getObject("user")!!)
            CacheManager.instance.setisLogin(true)
        }
        fetchLocations()
        fetchCategories()
        fetchPromotions()
        fetchProducts()
    }
    private fun fetchPromotions()
    {
        val call : Call<Promotions> = retrofit.getInitialData()
        call.enqueue(object : Callback<Promotions>
        {
            override fun onFailure(call: Call<Promotions>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<Promotions>?, response: Response<Promotions>?) {
                if(null != response?.body()?.data && response.body()?.data!!.isNotEmpty())
                {
                    CacheManager.instance.setInitialData(response.body()?.data!! as ArrayList<Promotions.Datum>)
                }
            }
        })
    }
    private fun fetchProducts()
    {
        val call : Call<ArrayList<Products>> = retrofit.getProducts()
        call.enqueue(object : Callback<ArrayList<Products>>
        {
            override fun onFailure(call: Call<ArrayList<Products>>?, t: Throwable?) {
                print(t.toString())
            }

            override fun onResponse(call: Call<ArrayList<Products>>?, response: Response<ArrayList<Products>>?) {
                if(null != response?.body() && response.body().isNotEmpty())
                {
                    CacheManager.instance.setProductsData(response.body()!! as ArrayList<Products>)
                    startActivity(Intent(this@SplashActivty,MainActivity::class.java))
                    finish()

                }
            }
        })
    }

    private fun fetchCategories()
    {
        val call : Call<ArrayList<Category>> = retrofit.getCategories()
        call.enqueue(object : Callback<ArrayList<Category>>
        {
            override fun onFailure(call: Call<ArrayList<Category>>?, t: Throwable?) {
                print(t.toString())
            }

            override fun onResponse(call: Call<ArrayList<Category>>?, response: Response<ArrayList<Category>>?) {
                if(null != response?.body() && response.body().isNotEmpty())
                {
                    CacheManager.instance.setCategoryData(response.body())
                }
            }
        })
    }
    private fun fetchLocations()
    {
        val call : Call<LocationResponse> = retrofit.getLocations()
        call.enqueue(object : Callback<LocationResponse>
        {
            override fun onFailure(call: Call<LocationResponse>?, t: Throwable?) {
                print(t.toString())
            }
            override fun onResponse(call: Call<LocationResponse>?, response: Response<LocationResponse>?) {
                if(null != response?.body() && response.body().data!!.isNotEmpty())
                {
                    CacheManager.instance.setLocationData(response.body().data!! as ArrayList<Cities>)
                }
            }
        })
    }
}