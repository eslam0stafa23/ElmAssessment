package com.assessment.elm.data.remote

import com.assessment.elm.data.requests.GetDashboardDataResponse
import retrofit2.http.GET

interface DashboardApiService {
  @GET("dashboard")
  suspend fun getDashboardData(): GetDashboardDataResponse
}