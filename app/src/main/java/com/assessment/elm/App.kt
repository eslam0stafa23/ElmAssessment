package com.assessment.elm

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.assessment.elm.di.AppComponent
import com.assessment.elm.di.ContextModule
import com.assessment.elm.di.DaggerAppComponent

class App : Application() {
  lateinit var appComponent: AppComponent
    private set

  override fun onCreate() {
    super.onCreate()
    appComponent = DaggerAppComponent.factory()
      .create(ContextModule(this))
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
  }
}