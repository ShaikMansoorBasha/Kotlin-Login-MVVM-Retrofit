package com.example.mvvm_architecture_android.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_architecture_android.data.network.MyAPI
import com.example.mvvm_architecture_android.ui.LoginModelClass
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    var loginModelClass:LoginModelClass?=null
    fun userLogin(email: String, password: String): LiveData<String> {
        val userLoginResponse = MutableLiveData<String>()
        MyAPI().userLogin(email, password)
            .enqueue(object : Callback<LoginModelClass> {
                override fun onResponse(
                    call: Call<LoginModelClass>,
                    response: Response<LoginModelClass>
                ) {
                    if (response.isSuccessful) {
                        loginModelClass=response.body()
                        Log.d("===", "onResponse: ${loginModelClass?.error}")
                        Log.d("===1", "onResponse: ${loginModelClass?.id}")
                        Log.d("===2", "onResponse: ${loginModelClass?.token}")
                        userLoginResponse.value = loginModelClass?.token
                    } else {
                        userLoginResponse.value = response.errorBody().toString()
                    }
                }

                override fun onFailure(call: Call<LoginModelClass>, t: Throwable) {
                    userLoginResponse.value = t.message

                }


            })
        return userLoginResponse

    }


}

