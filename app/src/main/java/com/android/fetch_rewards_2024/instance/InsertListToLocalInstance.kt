package com.android.fetch_rewards_2024.instance

import com.android.fetch_rewards_2024.model.FetchListModel
import com.android.fetch_rewards_2024.repo.RepositoryImpl

class InsertListToLocalInstance(private val dataRepository: RepositoryImpl) {
    suspend operator fun invoke(list: List<FetchListModel>) =
        dataRepository.insertFetchListToLocal(list)
}