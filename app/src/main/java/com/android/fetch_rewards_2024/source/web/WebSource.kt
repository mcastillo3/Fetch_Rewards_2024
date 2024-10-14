package com.android.fetch_rewards_2024.source.web

import com.android.fetch_rewards_2024.model.FetchListModel

interface WebSource {
    suspend fun getFetchList(): WebResult<List<FetchListModel>>
}