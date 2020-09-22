package com.lb.lahorebroast.profile

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lb.lahorebroast.MainActivity
import com.lb.lahorebroast.R
import com.lb.lahorebroast.model.RegisterationResponse
import com.lb.lahorebroast.order.MyOrders
import com.lb.lahorebroast.utilities.CacheManager
import com.lb.lahorebroast.utilities.TinyDB
import com.lb.lahorebroast.utilities.Utilities
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.pUserName

class ProfileFragment : Fragment() {
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(activity!!).get(ProfileViewModel::class.java)
        logout.setOnClickListener {
            showLogoutDialog()
        }
        updateProfile.setOnClickListener {
           val ft = activity!!.supportFragmentManager.beginTransaction()
            ft.replace(android.R.id.content,EditProfile()).addToBackStack(null).commit()
        }
        viewModel.getUserDataObject().observe(viewLifecycleOwner,object : Observer<RegisterationResponse.Data>
        {
            override fun onChanged(t: RegisterationResponse.Data?) {
                t?.let { initializeView(it) }
            }

        })
        orders.setOnClickListener {
            val fm = activity!!.supportFragmentManager.beginTransaction()
            fm.add(android.R.id.content, MyOrders()).addToBackStack(null).commit()
        }



    }
    private fun initializeView(user : RegisterationResponse.Data)
    {
        profileUserName.text = user.name
        pUserName.text = "Welcome"+", ${user.name}"
        profileEmail.text = user.email
        profileNumber.text = user.mobile
        profileAddress.text = user.shipping_address
    }

    private fun showLogoutDialog()
    {
        MaterialAlertDialogBuilder(activity,R.style.AlertDialogTheme)
            .setTitle("Logout")
            .setMessage("Are you sure, you want to Logout?")
            .setNeutralButton("cancel") { dialog, which ->
                // Respond to neutral button press
            }

            .setPositiveButton("Logout") { dialog, which ->
                TinyDB(activity!!).clear()
                startActivity(Intent(activity, MainActivity::class.java))
                activity!!.finish()
            }
            .show()
    }
}