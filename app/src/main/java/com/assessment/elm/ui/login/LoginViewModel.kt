package com.assessment.elm.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.assessment.elm.data.requests.LoginBody
import com.assessment.elm.domain.LoginUseCase
import com.assessment.elm.utils.asMappedResourceLiveData
import com.assessment.elm.utils.result.Resource
import javax.inject.Inject

class LoginViewModel @Inject constructor(
  private val loginUseCase: LoginUseCase
) : ViewModel() {

  fun login(loginBody: LoginBody): LiveData<Resource<String>> =
    loginUseCase.execute(loginBody).asMappedResourceLiveData("login")
}