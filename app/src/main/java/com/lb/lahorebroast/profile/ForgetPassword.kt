package com.lb.lahorebroast.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.lb.lahorebroast.R
import com.lb.lahorebroast.model.Category
import com.lb.lahorebroast.networking.APIClient
import com.lb.lahorebroast.networking.RequestService
import com.lb.lahorebroast.utilities.CacheManager
import com.lb.lahorebroast.utilities.Utilities
import kotlinx.android.synthetic.main.fragment_forget_password.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgetPassword : Fragment() {

    private var retrofit = APIClient.retrofitInstance.create(RequestService::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forget_password, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        changePassword.setOnClickListener {
            if(!phone_number.text.isNullOrEmpty())
            {
                if(phone_number.text.length == 11)
                {
                    Utilities.progressdialog(activity!!)
                    val phone = Utilities.countryCode+phone_number.text.toString()
                    val hashMap = HashMap<String,Any>()
                    hashMap["id"] = phone
                    val call : Call<ForgetPasswordResponse> = retrofit.forgetPassword(hashMap)
                    call.enqueue(object : Callback<ForgetPasswordResponse>
                    {
                        override fun onFailure(call: Call<ForgetPasswordResponse>?, t: Throwable?) {
                            print(t.toString())
                            Utilities.finishprogress()
                        }

                        override fun onResponse(call: Call<ForgetPasswordResponse>?, response: Response<ForgetPasswordResponse>?) {
                            Utilities.finishprogress()
                            if(null != response?.body() && response.body().success)
                            {
                                Toast.makeText(activity!!,"New password has been sent to registered phone number",Toast.LENGTH_SHORT).show()
                                activity!!.supportFragmentManager.popBackStack()
                            }else
                            {
                                Toast.makeText(activity!!,response?.body()?.message,Toast.LENGTH_SHORT).show()

                            }
                        }
                    })
                }else
                {
                    Toast.makeText(activity!!,"Phone number is incorrect",Toast.LENGTH_SHORT).show()
                }

            }else
            {
                Toast.makeText(activity!!,"Phone number can not be empty",Toast.LENGTH_SHORT).show()

            }
        }
    }


}