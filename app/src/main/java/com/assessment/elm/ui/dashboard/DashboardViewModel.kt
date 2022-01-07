package com.assessment.elm.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.assessment.elm.data.models.IncidentData
import com.assessment.elm.domain.GetIncidentsDataUseCase
import com.assessment.elm.utils.asMappedResourceLiveData
import com.assessment.elm.utils.result.Resource
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
  private val getIncidentsDataUseCase: GetIncidentsDataUseCase
) : ViewModel() {

  fun getDashboardData(): LiveData<Resource<List<IncidentData>>> =
    getIncidentsDataUseCase.execute().asMappedResourceLiveData("getDashboardData")
}