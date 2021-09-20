package com.example.mvvm_architecture_android.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

// Function to show  Long Toast
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

// Function to show Short Toast
fun Context.toastShort(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

// Function to show the ProgressBar
fun ProgressBar.show() {
    visibility = View.VISIBLE
}

// Function to Hide the ProgressBar
fun ProgressBar.hide() {
    visibility = View.GONE
}
