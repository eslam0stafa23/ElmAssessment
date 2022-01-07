package com.assessment.elm.domain

import com.assessment.elm.data.requests.LoginBody
import com.assessment.elm.utils.result.Resource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

  fun login(loginBody: LoginBody): Flow<Resource<String>>

}