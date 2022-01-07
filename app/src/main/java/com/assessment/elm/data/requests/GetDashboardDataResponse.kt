package com.assessment.elm.data.requests

import com.assessment.elm.data.models.IncidentData
import com.google.gson.annotations.SerializedName

data class GetDashboardDataResponse(
  @SerializedName("baseURL") val baseURL: String?,
  @SerializedName("incidents") val incidentsData: List<IncidentData>
)