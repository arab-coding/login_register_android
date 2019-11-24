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
import com.ih.ali.API.loginData
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_signin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignInAcvtivity : AppCompatActivity() {

    var myshered : SharedPreferences? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        sinb.setOnClickListener {
            val it =   Intent(this@SignInAcvtivity,main::class.java)
            startActivity(it)
        }
        sign_in.setOnClickListener {
            signIn(email_login.text.toString(),pswrd.text.toString())
        }

    }
    fun signIn( email:String ,password:String){
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: ApiService = retrofit.create(ApiService::class.java)
        val call = api.login(email,password)
        call.enqueue(object : Callback<loginData> {
            override fun onFailure(call: Call<loginData>, t: Throwable) {
                progressBarAS.visibility = View.GONE
                Toast.makeText(this@SignInAcvtivity,"غير قادر على الإتصال بالسيرفر" , Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<loginData>, response: Response<loginData>) {
                progressBarAS.visibility = View.GONE
                Toast.makeText(this@SignInAcvtivity,response.body()!!.api_token , Toast.LENGTH_LONG).show()
                myshered = getSharedPreferences("myshared", 0)
                var editor: SharedPreferences.Editor = myshered!!.edit()
                editor.putInt("id",response.body()!!.id)
                editor.putString("name",response.body()!!.name)
                editor.putString("email",response.body()!!.email)
                editor.putString("token",response.body()!!.api_token)
                editor.commit()
                val it =   Intent(this@SignInAcvtivity,welcome::class.java)
                startActivity(it)

            }

        })

    }



}
