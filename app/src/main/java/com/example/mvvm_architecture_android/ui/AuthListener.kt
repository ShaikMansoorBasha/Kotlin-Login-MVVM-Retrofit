package com.example.mvvm_architecture_android.ui.auth

import androidx.lifecycle.LiveData

interface AuthListener {
    // this method is use for when login operation started
    fun onStarted()
    // this method is use for when login operation Success
    fun onSuccess(loginResponse: LiveData<String>)
    // this method is use for when login operation Success
    fun onFailure(message:String)
}