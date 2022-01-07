package com.assessment.elm.data.requests

import com.google.gson.annotations.SerializedName

data class ChangeIncidentStatusBody(
  @SerializedName("incidentId") val incidentId: String,
  @SerializedName("status") val status: Int
)