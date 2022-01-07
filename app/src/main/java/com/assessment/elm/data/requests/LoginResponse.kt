package com.assessment.elm.data.requests

import com.google.gson.annotations.SerializedName

data class LoginResponse(
  @SerializedName("roles") val roles: List<Int>,
  @SerializedName("token") val token: String
)