package com.ih.ali


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_signin.*

class SignInAcvtivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        sinb.setOnClickListener {
            val it =   Intent(this@SignInAcvtivity,main::class.java)
            startActivity(it)
        }
    }


}
