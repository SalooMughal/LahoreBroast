package com.lb.lahorebroast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.lb.lahorebroast.model.RegisterationResponse
import com.lb.lahorebroast.utilities.TinyDB
import com.lb.lahorebroast.utilities.Utilities
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignUpFragment : Fragment(),SignUpViewModel.ServerResponse {

    lateinit var signUpViewModel: SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        signUpViewModel =  ViewModelProvider(this).get(SignUpViewModel::class.java)
        signUpViewModel.serverResponse = this
        register.setOnClickListener {
            if (rUserName.text.toString().isEmpty()) {
                Utilities.showAlertDialog(activity!!, "Please Check your UserName")
                return@setOnClickListener
            }
            if (email.text.toString().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(
                    email.text
                ).matches()
            ) {
                Utilities.showAlertDialog(activity!!, "Please Check your email")
                return@setOnClickListener
            }
            if (phone_number.text.toString().isEmpty()) {
                Utilities.showAlertDialog(activity!!, "Please Check your Phone Number")
                return@setOnClickListener
            }
            if (phone_number.text.length != 11) {
                Utilities.showAlertDialog(activity!!, "Please Format your number as '03234383941' ")
                return@setOnClickListener
            }
            if (password.text.toString().isEmpty()) {
                Utilities.showAlertDialog(activity!!, "Please Check your password")
                return@setOnClickListener
            }
            if (rUserName.text.toString().isNotEmpty() && email.text.toString().isNotEmpty() &&
                phone_number.text.toString().isNotEmpty() && password.text.toString()
                    .isNotEmpty() &&
                phone_number.text.length == 11 && android.util.Patterns.EMAIL_ADDRESS.matcher(email.text)
                    .matches()
            ) {
                Utilities.progressdialog(activity!!)
                val hashMap = HashMap<String, Any>()
                hashMap["name"] = rUserName.text.toString()
                hashMap["email"] = email.text.toString()
                hashMap["mobile"] = Utilities.countryCode + phone_number.text.toString()
                hashMap["customer_group_id"] = "2"
                hashMap["password"] = password.text.toString()

                signUpViewModel.registerUser(hashMap)
            }
        }
        signupBck.setOnClickListener {
            activity!!.supportFragmentManager.popBackStack()
        }

    }

    override fun onSuccessRegistration(user: RegisterationResponse.Data) {
        Utilities.finishprogress()
        TinyDB(activity!!).putObject("user",user)
        activity!!.finish()
    }

    override fun onFailure(message: String) {
        Utilities.finishprogress()
        Toast.makeText(activity!!,message,Toast.LENGTH_SHORT).show()

    }
}