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
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class Sign_upActivity : AppCompatActivity() {

    var myshered : SharedPreferences? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        sign_up.setOnClickListener {
            progressBarsu.visibility = View.VISIBLE
         signUp( name_su.text.toString(),mail_su.text.toString(),password_su.text.toString())

        }
        sback.setOnClickListener {
            val it = Intent(this@Sign_upActivity, main::class.java)
            startActivity(it)
        }
    }
    fun signUp(name:String , email:String ,password:String){
        val retrofit =Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api:ApiService = retrofit.create(ApiService::class.java)
        val call = api.register(name,email,password)
        call.enqueue(object :Callback<RegisterData>{
            override fun onFailure(call: Call<RegisterData>, t: Throwable) {
                progressBarsu.visibility = View.GONE
                Toast.makeText(this@Sign_upActivity,"غير قادر على الإتصال بالسيرفر" ,Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {
                progressBarsu.visibility = View.GONE
              Toast.makeText(this@Sign_upActivity,response.body()!!.api_token ,Toast.LENGTH_LONG).show()
                myshered = getSharedPreferences("myshared", 0)
                var editor:SharedPreferences.Editor = myshered!!.edit()
                editor.putString("token",response.body()!!.api_token)
                editor.commit()
                val it =   Intent(this@Sign_upActivity,welcome::class.java)
                startActivity(it)

            }

        })

    }

}
