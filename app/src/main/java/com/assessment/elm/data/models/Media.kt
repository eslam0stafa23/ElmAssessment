package com.assessment.elm.data.models

import com.google.gson.annotations.SerializedName

data class Media(
  @SerializedName("id") val id: String,
  @SerializedName("incidentId") val incidentId: String,
  @SerializedName("mimeType") val mimeType: String,
  @SerializedName("type") val type: Int,
  @SerializedName("url") val url: String
)