package com.assessment.elm.domain

import com.assessment.elm.data.requests.ChangeIncidentStatusBody
import com.assessment.elm.utils.result.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangeIncidentStatusUseCase @Inject constructor(private val homeRepository: HomeRepository) {
  fun execute(changeIncidentStatusBody: ChangeIncidentStatusBody): Flow<Resource<String>> =
    homeRepository.changeIncidentStatus(changeIncidentStatusBody)
}