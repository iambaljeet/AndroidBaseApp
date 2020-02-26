package com.app.baseapp.networking

import retrofit2.http.GET
import java.util.*

interface ApiService {
    @GET(NetworkingConstants.ApiEndPoints.GET_ARTICLES)
    fun getArticles(): Observable
}