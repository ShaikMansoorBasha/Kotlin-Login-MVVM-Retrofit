package com.example.mvvm_architecture_android.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvm_architecture_android.data.repositories.UserRepository

/**
 * Every ViewModel needs to Extends from ViewModel() class
 *
 */
class AuthViewModel : ViewModel() {
    // define variable initial value is null that why ? use as null safety operator
    var email: String? = null
    var password: String? = null

    // call auth listener interface for use as callback to activity
    var authListener: AuthListener? = null

    // function for if user click on login button
    fun onLoginButtonClick(view: View) {
        // when button click first we will call interface method on started here
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            // is user use wrong username of password/ set field in empty then call onFailure method from authListener
            authListener?.onFailure("Invalid Username or Password")
            // here return mean after execute the code it will be stop future execution
            return
        } else {
            // if Success call authListener method, !! this operator will sure it is not null
            val loginResponse = UserRepository().userLogin(email!!, password!!)
            // here i pass loginResponse here
            authListener?.onSuccess(loginResponse)

        }
    }
}