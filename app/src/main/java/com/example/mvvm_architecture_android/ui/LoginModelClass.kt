package com.example.mvvm_architecture_android.ui

import com.google.gson.annotations.SerializedName

data class LoginModelClass(

    @SerializedName("id") var id: Int,
    @SerializedName("token") var token: String,
    @SerializedName("error") var error: String

)