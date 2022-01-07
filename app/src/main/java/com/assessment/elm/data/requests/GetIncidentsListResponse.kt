package com.assessment.elm.data.requests

import com.assessment.elm.data.models.Incident
import com.google.gson.annotations.SerializedName

data class GetIncidentsListResponse(
  @SerializedName("baseURL") val baseURL: String,
  @SerializedName("incidents") val incidents: List<Incident>
)