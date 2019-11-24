package com.ih.ali

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_welcome.*


class welcome : AppCompatActivity() {
    var myshered : SharedPreferences? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        myshered = getSharedPreferences("myshared", 0)
        name.text = myshered?.getString("name","")
        email.text = myshered?.getString("email","")
    }

}
