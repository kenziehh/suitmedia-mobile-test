package com.suitmedia.mobile_test.core.models

import com.google.gson.annotations.SerializedName

data class UserRegres(
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val email: String,
    val avatar: String
)
