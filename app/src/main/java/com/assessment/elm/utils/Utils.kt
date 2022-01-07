package com.assessment.elm.utils

import android.content.res.Resources
import android.text.TextUtils
import com.assessment.elm.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

object Utils {

  fun formatApiDateTime(createdAt: String): String? {
    if (!TextUtils.isEmpty(createdAt)) {
      val oldFormat = SimpleDateFormat(Const.DATE_TIME_FORMAT, Locale.ENGLISH)
      val newFormat = SimpleDateFormat(Const.READABLE_DATE_TIME_FORMAT, Locale.ENGLISH)
      try {
        val date = oldFormat.parse(createdAt)
        return newFormat.format(date)
      } catch (e: ParseException) {
        e.printStackTrace()
      }
    }
    return null
  }

  fun convertToDateOnly(createdAt: String): String? {
    if (!TextUtils.isEmpty(createdAt)) {
      val oldFormat = SimpleDateFormat(Const.DATE_TIME_FORMAT, Locale.ENGLISH)
      val newFormat = SimpleDateFormat(Const.DATE_ONLY_FORMAT, Locale.ENGLISH)
      try {
        val date = oldFormat.parse(createdAt)
        return newFormat.format(date)
      } catch (e: ParseException) {
        e.printStackTrace()
      }
    }
    return null
  }

  fun getIncidentStatusString(status: Int): String{
    return when(status){
      0 -> "Under Investigation"
      1 -> "On Hold"
      2 -> "Open"
      3 -> "Resolved"
      else -> "New"
    }
  }

  fun getIncidentStatusInt(status: String): Int{
    return when(status){
      "Under Investigation" -> 0
      "On Hold" -> 1
      "Open" -> 2
      "Resolved" -> 3
      "New" -> 4
      else -> 5
    }
  }

  fun priorityColor(priority: Int): Int {
    return when (priority) {
      1 -> { R.color.red }
      2 -> { R.color.orange }
      3 -> { R.color.yellow }
      4 -> { R.color.blue }
      else -> { R.color.green }
    }
  }

  fun getStatusColorsList(resources: Resources): ArrayList<Int>{
    val colors: ArrayList<Int> = ArrayList()
    colors.add(resources.getColor(R.color.colorPrimary))
    colors.add(resources.getColor(R.color.colorPrimaryVariant))
    colors.add(resources.getColor(R.color.colorSecondary))
    colors.add(resources.getColor(R.color.colorSecondaryVariant))
    return colors
  }
}