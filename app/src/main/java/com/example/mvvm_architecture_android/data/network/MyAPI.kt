package com.example.mvvm_architecture_android.data.network

import com.example.mvvm_architecture_android.ui.LoginModelClass
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyAPI {
    // @Field value in "" should be same as api object name
    @FormUrlEncoded
    @POST("login")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginModelClass>

    companion object {
        operator fun invoke(): MyAPI {
            return Retrofit
                .Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyAPI::class.java)


        }

    }

}