package com.assessment.elm.domain

import com.assessment.elm.data.models.IncidentData
import com.assessment.elm.utils.result.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIncidentsDataUseCase @Inject constructor(private val dashboardRepository: DashboardRepository) {
  fun execute(): Flow<Resource<List<IncidentData>>> = dashboardRepository.getDashboardData()
}