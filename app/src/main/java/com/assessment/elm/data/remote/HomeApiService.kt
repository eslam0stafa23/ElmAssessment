package com.assessment.elm.data.remote

import com.assessment.elm.data.models.Incident
import com.assessment.elm.data.requests.ChangeIncidentStatusBody
import com.assessment.elm.data.requests.GetIncidentsListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface HomeApiService {
  @GET("incident")
  suspend fun getIncidentsList(
    @Query("startDate") cityName: String = "2021-11-14",
  ): GetIncidentsListResponse

  @PUT("incident/change-status")
  suspend fun changeIncidentStatus(@Body changeIncidentStatusBody: ChangeIncidentStatusBody): Incident
}