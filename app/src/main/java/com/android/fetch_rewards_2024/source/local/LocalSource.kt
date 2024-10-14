package com.android.fetch_rewards_2024.source.local

import com.android.fetch_rewards_2024.model.FetchListModel
import kotlinx.coroutines.flow.Flow

interface LocalSource {
    fun getList(): Flow<List<FetchListModel>>
    suspend fun insertList(list: List<FetchListModel>)
}