package com.android.fetch_rewards_2024.source.web

import com.android.fetch_rewards_2024.model.FetchListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WebSourceImpl(private val api: FetchListApi):WebSource {

    override suspend fun getFetchList(): WebResult<List<FetchListModel>> =
        withContext(Dispatchers.IO){
            try {
                val response = api.getFetchList()
                if (response.isSuccessful) return@withContext WebResult.Success(response.body()!!)
                else return@withContext WebResult.Error(Exception(response.message()))
            }catch (e:Exception){
                return@withContext WebResult.Error(e)
            }
        }
}