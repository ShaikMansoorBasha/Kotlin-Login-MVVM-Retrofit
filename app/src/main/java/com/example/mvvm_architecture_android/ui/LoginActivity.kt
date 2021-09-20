package com.example.mvvm_architecture_android.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_architecture_android.R
import com.example.mvvm_architecture_android.databinding.ActivityLoginBinding
import com.example.mvvm_architecture_android.ui.WelcomeActivity
import com.example.mvvm_architecture_android.util.hide
import com.example.mvvm_architecture_android.util.show
import com.example.mvvm_architecture_android.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // we use data binding instead of setContent view with will auto create a new name of ActivityLoginBinding
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        // Now we have to call viewModel here with viewModel class name in get method
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        // Now we set this view model to our binding viewModel
        binding.viewmodel = viewModel
        // Set the interface to viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {
        //Show the progress bar
        progress_bar.show()
        //Close the keyboard
        closeKeyBoard()

    }


    override fun onSuccess(loginResponse: LiveData<String>) {
        closeKeyBoard()
        loginResponse.observe(this, Observer {
            toast(it.toString())
            Log.d("====12345", "onSuccess: "+it+"==="+it)
            progress_bar.hide()
            //After Success navigate to Next Screen
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        })

    }

    override fun onFailure(message: String) {
        closeKeyBoard()
        //Hide the progress bar
        progress_bar.hide()
        //Toast message
        toast(message)

    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}