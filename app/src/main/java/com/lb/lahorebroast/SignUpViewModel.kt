package com.lb.lahorebroast

import androidx.lifecycle.ViewModel
import com.lb.lahorebroast.networking.APIClient
import com.lb.lahorebroast.model.RegisterationResponse
import com.lb.lahorebroast.networking.RequestService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {

    private var retrofit = APIClient.retrofitInstance.create(RequestService::class.java)
    lateinit var serverResponse : ServerResponse

    fun registerUser(hashMap: HashMap<String,Any>)
    {
        val call : Call<RegisterationResponse> = retrofit.userRegister(hashMap)
        call.enqueue(object : Callback<RegisterationResponse>
        {
            override fun onFailure(call: Call<RegisterationResponse>?, t: Throwable?) {
                serverResponse.onFailure("Unable to Register, please try again")
            }

            override fun onResponse(
                call: Call<RegisterationResponse>?,
                response: Response<RegisterationResponse>?
            ) {
               if(null != response?.body())
               {
                   if(response.body().success!!)
                   {
                       serverResponse.onSuccessRegistration(response.body().data!!)
                   }else
                   {
                       serverResponse.onFailure("Unable to Register, please try again")
                   }
               }else
               {
                   serverResponse.onFailure("Unable to Register, please try again")
               }
            }

        })
    }

    fun loginUser(hashMap: HashMap<String,Any>)
    {
        val call : Call<RegisterationResponse> = retrofit.userLogin(hashMap)
        call.enqueue(object : Callback<RegisterationResponse>
        {
            override fun onFailure(call: Call<RegisterationResponse>?, t: Throwable?) {
                serverResponse.onFailure("Unable to Login, please try again")
            }

            override fun onResponse(
                call: Call<RegisterationResponse>?,
                response: Response<RegisterationResponse>?
            ) {
                if(null != response?.body())
                {
                    if(response.body().success!!)
                    {
                        serverResponse.onSuccessRegistration(response.body().data!!)
                    }else
                    {
                        serverResponse.onFailure("Username and password not matched, please try again")
                    }
                }else
                {
                    serverResponse.onFailure("Unable to Login, please try again")
                }
            }

        })
    }
    interface  ServerResponse
    {
        fun onSuccessRegistration(user : RegisterationResponse.Data)
        fun onFailure(message : String)
    }
}