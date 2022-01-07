package com.assessment.elm.domain

import com.assessment.elm.data.requests.LoginBody
import com.assessment.elm.utils.result.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
  fun execute(loginBody: LoginBody): Flow<Resource<String>> =
    loginRepository.login(loginBody)
}