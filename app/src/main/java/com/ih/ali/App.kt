package com.ih.ali

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity

class App : AppCompatActivity() {
    var myshered : SharedPreferences? =null
    override fun onStart() {
        super.onStart()
        myshered = getSharedPreferences("myshared", 0)
        var token = myshered?.getString("token","")

            if(token != ""){
                val it =   Intent(this@App,welcome::class.java)
                startActivity(it)
            }else if (token == ""){
                val it =   Intent(this@App,main::class.java)
                startActivity(it)
            }
    }
}