package com.assessment.elm.di

import androidx.lifecycle.ViewModel
import com.assessment.elm.di.annotitions.ViewModelKey
import com.assessment.elm.ui.dashboard.DashboardViewModel
import com.assessment.elm.ui.home.HomeViewModel
import com.assessment.elm.ui.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
  @Binds @IntoMap @ViewModelKey(LoginViewModel::class)
  abstract fun provideLoginViewModel(loginViewModel: LoginViewModel): ViewModel

  @Binds @IntoMap @ViewModelKey(HomeViewModel::class)
  abstract fun provideHomeViewModel(homeViewModel: HomeViewModel): ViewModel

  @Binds @IntoMap @ViewModelKey(DashboardViewModel::class)
  abstract fun provideDashboardViewModel(dashboardViewModel: DashboardViewModel): ViewModel
}