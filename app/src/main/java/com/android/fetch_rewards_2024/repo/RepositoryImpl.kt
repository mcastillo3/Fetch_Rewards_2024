package com.android.fetch_rewards_2024.repo

import com.android.fetch_rewards_2024.model.FetchListModel
import com.android.fetch_rewards_2024.source.local.LocalSource
import com.android.fetch_rewards_2024.source.web.WebResult
import com.android.fetch_rewards_2024.source.web.WebSource
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val webSource: WebSource,
    private val localSource: LocalSource): Repository {

    override suspend fun getFetchListFromWeb(): WebResult<List<FetchListModel>> =
        webSource.getFetchList()

    override fun getFetchListFromLocal(): Flow<List<FetchListModel>> = localSource.getList()
    override suspend fun insertFetchListToLocal(list: List<FetchListModel>) =
        localSource.insertList(list)

}