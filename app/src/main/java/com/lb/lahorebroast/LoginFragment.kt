package com.lb.lahorebroast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.lb.lahorebroast.model.RegisterationResponse
import com.lb.lahorebroast.profile.ForgetPassword
import com.lb.lahorebroast.utilities.TinyDB
import com.lb.lahorebroast.utilities.Utilities
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() , SignUpViewModel.ServerResponse{


    private var token: String? = null
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        signUpViewModel =  ViewModelProvider(this).get(SignUpViewModel::class.java)
        signUpViewModel.serverResponse = this
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                 token = task.result

            })
        userRegister.setOnClickListener {
            val ft = activity!!.supportFragmentManager.beginTransaction()
            ft.replace(android.R.id.content, SignUpFragment()).setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            ).addToBackStack(null).commit()
        }
        forget.setOnClickListener {
            val ft = activity!!.supportFragmentManager.beginTransaction()
            ft.replace(android.R.id.content, ForgetPassword()).setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            ).addToBackStack(null).commit()
        }
        login.setOnClickListener {
            if (phone_number.text.toString().isEmpty()) {
                Utilities.showAlertDialog(activity!!, "Please Check your Phone Number")
                return@setOnClickListener
            }
            if (phone_number.text.length != 11) {
                Utilities.showAlertDialog(activity!!, "Please Format your number as '03234383941' ")
                return@setOnClickListener
            }
            if (passwordProfile.text.toString().isEmpty()) {
                Utilities.showAlertDialog(activity!!, "Please Check your password")
                return@setOnClickListener
            }
            if (phone_number.text.toString().isNotEmpty() && passwordProfile.text.toString()
                    .isNotEmpty() &&
                phone_number.text.length == 11) {
                Utilities.progressdialog(activity!!)
                val hashMap = HashMap<String, Any>()
                hashMap["username"] = Utilities.countryCode + phone_number.text.toString()
                hashMap["password"] = passwordProfile.text.toString()
                if(!token.isNullOrEmpty()) {
                    hashMap["device_token"] = token.toString()
                }
                signUpViewModel.loginUser(hashMap)
            }

        }

    }

    override fun onSuccessRegistration(user: RegisterationResponse.Data) {
        Utilities.finishprogress()
        TinyDB(activity!!).putObject("user", user)
        activity!!.finish()
    }

    override fun onFailure(message: String) {
        Utilities.finishprogress()
        Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()

    }
}