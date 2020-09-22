package com.lb.lahorebroast.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lb.lahorebroast.R
import com.lb.lahorebroast.model.RegisterationResponse
import com.lb.lahorebroast.utilities.TinyDB
import com.lb.lahorebroast.utilities.Utilities
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_edit_profile.pUserName
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.profile_fragment.*

class EditProfile : Fragment() , ProfileViewModel.ServerResponse {

    private lateinit var viewModel: ProfileViewModel
    private var user = RegisterationResponse.Data()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(activity!!).get(ProfileViewModel::class.java)
        viewModel.serverResponse = this
        previous.setOnClickListener {
            activity!!.supportFragmentManager.popBackStack()
        }
        cancelEdit.setOnClickListener {
            activity!!.supportFragmentManager.popBackStack()
        }
        viewModel.getUserDataObject().observe(viewLifecycleOwner,object :
            Observer<RegisterationResponse.Data>
        {
            override fun onChanged(t: RegisterationResponse.Data?) {
                t?.let {
                    user = t
                    initializeView(it) }
            }

        })
        updateEdit.setOnClickListener {
            val hashMap = HashMap<String,Any>()
            hashMap["id"] = user.id!!
            hashMap["name"] = editUserName.text.toString()
            hashMap["email"] = editEmail.text.toString()
            hashMap["mobile"] = editNumber.text.toString()
            hashMap["address"] = editAddress.text.toString()
                if (editUserName.text.toString().isEmpty()) {
                    Utilities.showAlertDialog(activity!!, "Please Check your UserName")
                    return@setOnClickListener
                }
                if (editEmail.text.toString().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(
                        editEmail.text
                    ).matches()
                ) {
                    Utilities.showAlertDialog(activity!!, "Please Check your email")
                    return@setOnClickListener
                }
                if (editNumber.text.toString().isEmpty()) {
                    Utilities.showAlertDialog(activity!!, "Please Check your Phone Number")
                    return@setOnClickListener
                }
                if (editNumber.text.length < 11) {
                    Utilities.showAlertDialog(
                        activity!!,
                        "Please Format your number as '+9203234383941' "
                    )
                    return@setOnClickListener
                }

                if (editUserName.text.toString().isNotEmpty() && editEmail.text.toString().isNotEmpty() &&
                    editNumber.text.toString().isNotEmpty() &&
                    editNumber.text.length >= 11 && android.util.Patterns.EMAIL_ADDRESS.matcher(
                        editEmail.text).matches() && editAddress.text.toString().isNotEmpty()
                ) {
                    Utilities.progressdialog(activity!!)
                    viewModel.updateUserProfile(hashMap)
                }
        }
    }
    private fun initializeView(user : RegisterationResponse.Data)
    {
        editUserName.setText(user.name)
        editEmail.setText(user.email)
        editNumber.setText(user.mobile)
        editAddress.setText(user.shipping_address)
    }

    override fun success(data: RegisterationResponse.Data) {
        Utilities.finishprogress()
        TinyDB(activity!!).putObject("user",data)
        activity!!.supportFragmentManager.popBackStack()
    }

    override fun failure(message: String) {
        Utilities.finishprogress()
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
    }
}