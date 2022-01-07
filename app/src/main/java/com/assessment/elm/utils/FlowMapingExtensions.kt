package com.assessment.elm.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.assessment.elm.utils.result.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

fun <T> Flow<Resource<T>>.asMappedResourceLiveData(
  tag: String = "",
  coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
): LiveData<Resource<T>> {
  return onStart { emit(Resource.loading()) }
    .catch {
      Log.e("asMappedResourceLiveData", "asMappedResourceLiveData: ${it.message}")
      emit(Resource.error(it.message))
    }
    .asLiveData(coroutineDispatcher)
}