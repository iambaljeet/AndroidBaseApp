package com.architecture.project.model

data class GenericDataModel<T>(val isSuccess: Boolean?, val data: T?, val message: String?, val isLoading: Boolean?)