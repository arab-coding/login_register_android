package com.ih.ali.API

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("register")
    @FormUrlEncoded
    @Headers("Accept: application/json")
    fun register(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String
    ): Call<RegisterData>

}