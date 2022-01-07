package com.assessment.elm.di

import com.assessment.elm.ui.MainActivity
import com.assessment.elm.ui.dashboard.DashboardFragment
import com.assessment.elm.ui.home.ChangeIncidentStatusBottomSheet
import com.assessment.elm.ui.home.HomeFragment
import com.assessment.elm.ui.login.LoginFragment
import com.assessment.elm.ui.login.VerifyOtpFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    RepositoriesModule::class, SessionManagerModule::class, WebServiceModule::class, ContextModule::class, ViewModelModule::class
  ]
)
interface AppComponent {
  fun inject(mainActivity: MainActivity)
  fun inject(loginFragment: LoginFragment)
  fun inject(verifyOtpFragment: VerifyOtpFragment)
  fun inject(homeFragment: HomeFragment)
  fun inject(dashboardFragment: DashboardFragment)
  fun inject(changeIncidentStatusBottomSheet: ChangeIncidentStatusBottomSheet)

  @Component.Factory
  interface Factory {
    fun create(contextModule: ContextModule): AppComponent
  }
}