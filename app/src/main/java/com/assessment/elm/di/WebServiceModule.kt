package com.assessment.elm.di

import com.assessment.elm.BuildConfig
import com.assessment.elm.data.remote.DashboardApiService
import com.assessment.elm.data.remote.HomeApiService
import com.assessment.elm.data.remote.LoginApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class WebServiceModule {
  @Singleton @Provides
  fun provideOkHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
      .connectTimeout(1, TimeUnit.MINUTES)
      .readTimeout(1, TimeUnit.MINUTES)
      .addInterceptor(logging)
      .build()
  }

  private fun buildRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BuildConfig.MAIN_URL)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Singleton @Provides
  fun provideMainRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return buildRetrofit(okHttpClient)
  }

  @Provides @Singleton
  fun provideLoginApiService(retrofit: Retrofit): LoginApiService {
    return retrofit.create(LoginApiService::class.java)
  }

  @Provides @Singleton
  fun provideHomeApiService(retrofit: Retrofit): HomeApiService {
    return retrofit.create(HomeApiService::class.java)
  }

  @Provides @Singleton
  fun provideDashboardApiService(retrofit: Retrofit): DashboardApiService {
    return retrofit.create(DashboardApiService::class.java)
  }
}