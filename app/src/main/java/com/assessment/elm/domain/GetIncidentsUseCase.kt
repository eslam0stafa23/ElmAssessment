package com.assessment.elm.domain

import com.assessment.elm.data.models.Incident
import com.assessment.elm.utils.result.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIncidentsUseCase @Inject constructor(private val homeRepository: HomeRepository) {
  fun execute(): Flow<Resource<List<Incident>>> = homeRepository.getIncidentsList()
}