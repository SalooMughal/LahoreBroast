package com.lb.lahorebroast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private var action = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if( null != intent.getStringExtra("action")) {
            action = intent.getStringExtra("action")
        }

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.mContainer,HomeFragment(action)).setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right).commit()
    }
}