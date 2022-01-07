package com.assessment.elm.data

import com.assessment.elm.data.models.IncidentData
import com.assessment.elm.data.remote.DashboardApiService
import com.assessment.elm.domain.DashboardRepository
import com.assessment.elm.utils.result.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(private val dashboardApiService: DashboardApiService) :
  DashboardRepository {

  /**
   * this method is used to get a list of Incidents
   * @return Flow<Resource<List<Incident>>> return success or error if success it will include the list
   */
  override fun getDashboardData(): Flow<Resource<List<IncidentData>>> {
    return flow {
      emit(Resource.loading())
      try {
        val apiResponse = dashboardApiService.getDashboardData()
        emit(Resource.success(apiResponse.incidentsData))
      } catch (e: IOException) {
        emit(Resource.error())
      }
    }
  }
}

