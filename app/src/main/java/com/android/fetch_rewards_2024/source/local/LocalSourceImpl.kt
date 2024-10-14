package com.android.fetch_rewards_2024.source.local

import com.android.fetch_rewards_2024.model.FetchListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalSourceImpl(private val dao: FetchListDao): LocalSource {

    override fun getList(): Flow<List<FetchListModel>> = dao.getList()

    override suspend fun insertList(list: List<FetchListModel>) =
        withContext(Dispatchers.IO){
            dao.insertList(list)
        }
}