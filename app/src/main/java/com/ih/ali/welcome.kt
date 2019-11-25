package com.ih.ali

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ih.ali.API.ApiService
import com.ih.ali.API.RegisterData
import com.ih.ali.API.URL
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_welcome.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class welcome : AppCompatActivity() {
    var myshered : SharedPreferences? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        myshered = getSharedPreferences("myshared", 0)
        name.text = myshered?.getString("name","")
        email.text = myshered?.getString("email","")

        logout.setOnClickListener{
            logout()
        }
    }
    fun logout(){
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: ApiService = retrofit.create(ApiService::class.java)
        val call = api.logout(myshered!!.getString("token",""))
        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@welcome,"غير قادر على الإتصال بالسيرفر" , Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Toast.makeText(this@welcome,response.message() , Toast.LENGTH_LONG).show()
                myshered?.edit()!!.clear().commit()
                val it =   Intent(this@welcome,main::class.java)
                startActivity(it)

            }

        })

    }
}
