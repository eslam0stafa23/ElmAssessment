package com.assessment.elm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.assessment.elm.data.models.Incident
import com.assessment.elm.data.requests.ChangeIncidentStatusBody
import com.assessment.elm.domain.ChangeIncidentStatusUseCase
import com.assessment.elm.domain.GetIncidentsUseCase
import com.assessment.elm.utils.asMappedResourceLiveData
import com.assessment.elm.utils.result.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(
  private val getIncidentsUseCase: GetIncidentsUseCase,
  private val changeIncidentStatusUseCase: ChangeIncidentStatusUseCase
) : ViewModel() {

  fun getIncidentsList(): LiveData<Resource<List<Incident>>> =
    getIncidentsUseCase.execute().asMappedResourceLiveData("getIncidentsList")

  fun changeIncidentStatus(changeIncidentStatusBody: ChangeIncidentStatusBody): LiveData<Resource<String>> =
    changeIncidentStatusUseCase.execute(changeIncidentStatusBody)
      .asMappedResourceLiveData("changeIncidentStatus")

}