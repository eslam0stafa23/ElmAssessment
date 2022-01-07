package com.assessment.elm.data.requests

import com.google.gson.annotations.SerializedName

data class LoginBody(
  @SerializedName("email") val email: String,
  @SerializedName("otp") val otp: String
)