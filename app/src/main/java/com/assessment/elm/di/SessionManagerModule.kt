package com.assessment.elm.di

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SessionManagerModule {

  @Singleton @Provides fun providePrefs(context: Context): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
  }

  @Singleton @Provides fun providePrefsEditor(sharedPreferences: SharedPreferences): Editor {
    return sharedPreferences.edit()
  }
}