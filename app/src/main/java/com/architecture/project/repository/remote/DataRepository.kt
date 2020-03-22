package com.architecture.project.repository.remote

import androidx.lifecycle.MutableLiveData
import com.architecture.project.model.GenericDataModel
import com.architecture.project.model.SearchResultModel
import com.architecture.project.networking.ApiProvider
import com.architecture.project.networking.ApiService

object DataRepository {
    var apiService: ApiService? = null
    var searchResultsLiveData: MutableLiveData<GenericDataModel<SearchResultModel>?> = MutableLiveData()

    init {
        apiService =
            ApiProvider.createService(
                ApiService::class.java
            )
    }

    suspend fun getSearchResults(searchQuery: String?) {
        val genericDataModelLoading =
            GenericDataModel(
                null,
                SearchResultModel(
                    null,
                    null
                ),
                null,
                true
            )
        searchResultsLiveData.postValue(genericDataModelLoading)
        val result = apiService?.getResult(searchQuery)
        val genericDataModel =
            GenericDataModel(
                result?.isSuccessful,
                result?.body(),
                result?.message(),
                false
            )
        searchResultsLiveData.postValue(genericDataModel)
    }
}