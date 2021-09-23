package com.example.mvvm_architecture_android.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_architecture_android.data.network.MyAPI
import com.example.mvvm_architecture_android.ui.LoginModelClass
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository {
    var loginModelClass: LoginModelClass? = null
    fun userLogin(email: String, password: String): LiveData<String> {
        val userLoginResponse = MutableLiveData<String>()
        MyAPI().userLogin(email, password)
            .enqueue(object : Callback<LoginModelClass> {
                override fun onResponse(
                    call: Call<LoginModelClass>,
                    response: Response<LoginModelClass>
                ) {
                    if (response.isSuccessful) {
                        loginModelClass = response.body()
                        userLoginResponse.value = loginModelClass?.token
                    } else if (response.code() == 400) {
                        val gson = Gson()
                        val message: LoginModelClass = gson.fromJson(response.errorBody()!!.charStream(), LoginModelClass::class.java)
                        userLoginResponse.value = message.error

                    } else {
                        loginModelClass = response.body()
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

