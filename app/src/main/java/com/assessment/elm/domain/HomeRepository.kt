package com.assessment.elm.domain

import com.assessment.elm.data.models.Incident
import com.assessment.elm.data.requests.ChangeIncidentStatusBody
import com.assessment.elm.utils.result.Resource
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

  fun getIncidentsList(): Flow<Resource<List<Incident>>>

  fun changeIncidentStatus(changeIncidentStatusBody: ChangeIncidentStatusBody): Flow<Resource<String>>

}