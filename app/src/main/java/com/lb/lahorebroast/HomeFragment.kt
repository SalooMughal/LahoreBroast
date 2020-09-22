package com.lb.lahorebroast

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lb.lahorebroast.cart.CartFragment
import com.lb.lahorebroast.categoriespage.Categories
import com.lb.lahorebroast.order.MyOrders
import com.lb.lahorebroast.profile.ProfileFragment
import com.lb.lahorebroast.utilities.CacheManager
import com.lb.lahorebroast.utilities.Utilities
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment(val action : String) : Fragment() {
    private lateinit var ft : FragmentTransaction
    var lastIndex = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ft = activity!!.supportFragmentManager.beginTransaction()
        ft.replace(R.id.frame_home_main,HomeScreen()).commit()
        var bNav : BottomNavigationView = view!!.findViewById(R.id.navigation_view)

        bNav.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem) : Boolean {
                ft = activity!!.supportFragmentManager.beginTransaction()
                when (item.itemId) {
                    R.id.home -> {
                        ft.replace(R.id.frame_home_main, HomeScreen())
                        ft.commit()
                        return true

                    }
                    R.id.menu -> {

                        ft.replace(R.id.frame_home_main, Categories())
                        ft.commit()
                        return true

                    }
                    R.id.cart -> {
                        ft = activity!!.supportFragmentManager.beginTransaction()
                        ft.replace(android.R.id.content, CartFragment())
                        ft.addToBackStack(null).commit()

                        return true
                    }
                    R.id.profile -> {
                        if(Utilities.isUserLogin(activity!!)) {
                            ft.replace(R.id.frame_home_main, ProfileFragment())
                            ft.commit()
                        }else
                        {
                            startActivity(Intent(activity!!,LoginSignupActivity::class.java))
                        }
                        return true
                    }
                    else -> {
                        return false
                    }


                }

            }
        })
        if(CacheManager.instance.getIsLogin()) {
            if (action == "order") {
                val fm = activity!!.supportFragmentManager.beginTransaction()
                fm.add(android.R.id.content, MyOrders()).addToBackStack(null).commit()
            }else if(action == "addItems")
            {
                navigation_view.selectedItemId = R.id.menu
            }
        }
        }
    internal fun BottomNavigationView.checkItem(actionId: Int) {
        menu.findItem(actionId)?.isChecked = true
    }
    internal fun BottomNavigationView.uncheckItem(actionId: Int) {
        menu.findItem(actionId)?.isChecked = false
    }
}