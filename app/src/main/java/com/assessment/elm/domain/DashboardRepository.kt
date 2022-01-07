package com.assessment.elm.domain

import com.assessment.elm.data.models.IncidentData
import com.assessment.elm.utils.result.Resource
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {

  fun getDashboardData(): Flow<Resource<List<IncidentData>>>

}