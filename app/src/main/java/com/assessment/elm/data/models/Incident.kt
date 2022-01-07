package com.assessment.elm.data.models

import com.google.gson.annotations.SerializedName

data class Incident(

  @SerializedName("description") val description: String,
  @SerializedName("createdAt") val createdAt: String,
  @SerializedName("updatedAt") val updatedAt: String,
  @SerializedName("latitude") val latitude: Double,
  @SerializedName("longitude") val longitude: Double,
  @SerializedName("medias") val medias: List<Media>,
  @SerializedName("priority") val priority: Int,
  @SerializedName("status") val status: Int,
  @SerializedName("assigneeId") val assigneeId: String,
  @SerializedName("id") val id: String,
  @SerializedName("issuerId") val issuerId: String,
  @SerializedName("typeId") val typeId: Int,

  var createdAtDate: String?
)