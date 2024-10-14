package com.android.fetch_rewards_2024.source.web

import com.android.fetch_rewards_2024.model.FetchListModel
import retrofit2.Response
import retrofit2.http.GET

interface FetchListApi {

    @GET("hiring.json")
    suspend fun getFetchList(): Response<List<FetchListModel>>
}