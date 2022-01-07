package com.assessment.elm.data.models

import com.google.gson.annotations.SerializedName

data class IncidentData(
  @SerializedName("_count") val count: Count,
  @SerializedName("status") val status: Int
)