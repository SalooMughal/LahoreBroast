package com.lb.lahorebroast.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lb.lahorebroast.networking.APIClient
import com.lb.lahorebroast.model.RegisterationResponse
import com.lb.lahorebroast.networking.RequestService
import com.lb.lahorebroast.utilities.CacheManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {
    private var retrofit = APIClient.retrofitInstance.create(RequestService::class.java)
    private var user: MutableLiveData<RegisterationResponse.Data> = MutableLiveData()
    lateinit var serverResponse : ServerResponse

    init {
        if(CacheManager.instance.getIsLogin()) {
            user.value = CacheManager.instance.getUserData()
        }
    }

    fun getUserDataObject():LiveData<RegisterationResponse.Data>
    {
        return user
    }
    fun updateUserProfile(data : HashMap<String,Any>)
    {
        val call : Call<RegisterationResponse> = retrofit.userUpdate(data)
        call.enqueue(object : Callback<RegisterationResponse>
        {
            override fun onFailure(call: Call<RegisterationResponse>?, t: Throwable?) {
             serverResponse.failure("unable to update profile please try again")
            }

            override fun onResponse(
                call: Call<RegisterationResponse>?,
                response: Response<RegisterationResponse>?
            ) {
                if(null != response?.body())
                {
                    if(response.body().success!!)
                    {
                        serverResponse.success(response.body().data!!)
                        user.value = response.body().data!!
                    }else
                    {
                        serverResponse.failure("unable to update profile please try again")
                    }
                }else
                {
                    serverResponse.failure("unable to update profile please try again")
                }

            }

        })



    }
    interface ServerResponse
    {
        fun success(data : RegisterationResponse.Data)
        fun failure(message : String)
    }

}