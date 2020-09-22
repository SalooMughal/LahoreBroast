package com.lb.lahorebroast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoginSignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_signup)
        val ft = supportFragmentManager.beginTransaction()
        ft.add(android.R.id.content,LoginFragment()).commit()
    }
}