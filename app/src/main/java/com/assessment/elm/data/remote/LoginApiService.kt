package com.assessment.elm.data.remote

import com.assessment.elm.data.requests.LoginBody
import com.assessment.elm.data.requests.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
  @POST("verify-otp")
  suspend fun login(@Body loginBody: LoginBody): LoginResponse
}