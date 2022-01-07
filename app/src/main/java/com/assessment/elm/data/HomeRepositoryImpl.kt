package com.assessment.elm.data

import com.assessment.elm.data.models.Incident
import com.assessment.elm.data.remote.HomeApiService
import com.assessment.elm.data.requests.ChangeIncidentStatusBody
import com.assessment.elm.domain.HomeRepository
import com.assessment.elm.utils.result.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeApiService: HomeApiService) :
  HomeRepository {

  /**
   * this method is used to get a list of Incidents
   * @return Flow<Resource<List<Incident>>> return success or error if success it will include the list
   */
  override fun getIncidentsList(): Flow<Resource<List<Incident>>> {
    return flow {
      emit(Resource.loading())
      try {
        val apiResponse = homeApiService.getIncidentsList()
        emit(Resource.success(apiResponse.incidents))
      } catch (e: IOException) {
        emit(Resource.error())
      }
    }
  }

  override fun changeIncidentStatus(changeIncidentStatusBody: ChangeIncidentStatusBody): Flow<Resource<String>> {
    return flow {
      emit(Resource.loading())
      try {
        val apiResponse = homeApiService.changeIncidentStatus(changeIncidentStatusBody)
        emit(Resource.success(apiResponse.id))
      } catch (e: IOException) {
        emit(Resource.error())
      }
    }
  }
}

