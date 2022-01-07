package com.assessment.elm.utils

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class SessionManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

  companion object {
    const val AUTH_TOKEN = "authToken"
  }

  var accessToken: String?
    get() = sharedPreferences.getString(AUTH_TOKEN, "")
    set(value) = sharedPreferences.edit { putString(AUTH_TOKEN, value) }

}


