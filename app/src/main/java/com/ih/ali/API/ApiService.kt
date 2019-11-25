package com.ih.ali.API

import okhttp3.ResponseBody
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

    @POST("login")
    @FormUrlEncoded
    @Headers("Accept: application/json")
    fun login(
        @Field("email") email:String,
        @Field("password") password:String
    ): Call<loginData>

    @POST("logout")
    @FormUrlEncoded
    @Headers("Accept: application/json")
    fun logout(
        @Field("api_token") token:String

    ): Call<ResponseBody>

}