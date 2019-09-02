package com.ih.ali


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sign_up.setOnClickListener {
            val it =   Intent(this@main,SignInAcvtivity::class.java)
            startActivity(it)
        }
        signupforfree.setOnClickListener {
         val it =   Intent(this@main,Sign_upActivity::class.java)
            startActivity(it)
        }


    }
}
