package com.android.fetch_rewards_2024.repo

import com.android.fetch_rewards_2024.model.FetchListModel
import com.android.fetch_rewards_2024.source.web.WebResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getFetchListFromWeb(): WebResult<List<FetchListModel>>

    fun getFetchListFromLocal(): Flow<List<FetchListModel>>
    suspend fun insertFetchListToLocal(list: List<FetchListModel>)
}