package com.android.fetch_rewards_2024.instance

import com.android.fetch_rewards_2024.repo.RepositoryImpl

class GetListFromLocalInstance(private val dataRepository: RepositoryImpl) {
    operator fun invoke() = dataRepository.getFetchListFromLocal()
}