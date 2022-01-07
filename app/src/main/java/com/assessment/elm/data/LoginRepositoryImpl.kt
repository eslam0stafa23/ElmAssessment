package com.assessment.elm.data

import com.assessment.elm.data.remote.LoginApiService
import com.assessment.elm.data.requests.LoginBody
import com.assessment.elm.domain.LoginRepository
import com.assessment.elm.utils.result.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginApiService: LoginApiService) :
  LoginRepository {

  /**
   * this method is used to login the user
   * @param loginBody String
   * @return Flow<Resource<String>> return success or error if success it will include the token
   */
  override fun login(loginBody: LoginBody): Flow<Resource<String>> {
    return flow {
      emit(Resource.loading())
      try {
        val apiResponse = loginApiService.login(loginBody)
        emit(Resource.success(apiResponse.token))
      } catch (e: IOException) {
        emit(Resource.error())
      }
    }
  }
}

