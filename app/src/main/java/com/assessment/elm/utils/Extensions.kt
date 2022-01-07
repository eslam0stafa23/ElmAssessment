package com.assessment.elm.utils

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.assessment.elm.App
import com.assessment.elm.R
import com.assessment.elm.di.AppComponent
import kotlin.math.round

val Context.app: App get() = applicationContext as App

/**
 * enable dagger AppComponent for any Context
 */
val Context.appComponent: AppComponent get() = app.appComponent

/**
 * enable dagger AppComponent for any Fragment
 */
val Fragment.appComponent: AppComponent
  get() = requireContext().app.appComponent

val ViewGroup.layoutInflater: LayoutInflater get() = LayoutInflater.from(this.context)

fun Activity.hideSoftKeyboard() {
  val inputMethodManager =
    this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
  if (this.hasWindowFocus()) {
    inputMethodManager.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
  }
}