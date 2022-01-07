package com.assessment.elm.di

import com.assessment.elm.data.DashboardRepositoryImpl
import com.assessment.elm.data.HomeRepositoryImpl
import com.assessment.elm.data.LoginRepositoryImpl
import com.assessment.elm.domain.DashboardRepository
import com.assessment.elm.domain.HomeRepository
import com.assessment.elm.domain.LoginRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoriesModule {
  @Singleton @Binds
  abstract fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository?

  @Singleton @Binds
  abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository?

  @Singleton @Binds
  abstract fun bindDashboardRepository(dashboardRepositoryImpl: DashboardRepositoryImpl): DashboardRepository?
}