package com.lb.lahorebroast

import android.os.Bundle
import android.transition.TransitionSet
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.lb.lahorebroast.model.RegisterationResponse
import com.lb.lahorebroast.utilities.TinyDB
import com.lb.lahorebroast.utilities.Utilities
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.password
import kotlinx.android.synthetic.main.fragment_login.phone_number
import kotlinx.android.synthetic.main.fragment_sign_up.*

class LoginFragment : Fragment() , SignUpViewModel.ServerResponse{


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
        userRegister.setOnClickListener {
            val ft = activity!!.supportFragmentManager.beginTransaction()
            ft.replace(android.R.id.content,SignUpFragment()).setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right).addToBackStack(null).commit()
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
            if (password.text.toString().isEmpty()) {
                Utilities.showAlertDialog(activity!!, "Please Check your password")
                return@setOnClickListener
            }
            if (phone_number.text.toString().isNotEmpty() && password.text.toString()
                    .isNotEmpty() &&
                phone_number.text.length == 11) {
                Utilities.progressdialog(activity!!)
                val hashMap = HashMap<String, Any>()
                hashMap["username"] = Utilities.countryCode + phone_number.text.toString()
                hashMap["password"] = password.text.toString()
                signUpViewModel.loginUser(hashMap)
            }

        }

    }

    override fun onSuccessRegistration(user: RegisterationResponse.Data) {
        Utilities.finishprogress()
        TinyDB(activity!!).putObject("user",user)
        activity!!.finish()
    }

    override fun onFailure(message: String) {
        Utilities.finishprogress()
        Toast.makeText(activity!!,message, Toast.LENGTH_SHORT).show()

    }
}