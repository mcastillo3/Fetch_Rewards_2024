package com.android.fetch_rewards_2024.instance

import com.android.fetch_rewards_2024.repo.Repository

class GetListFromWebInstance(private val repository: Repository) {
    suspend operator fun invoke() = repository.getFetchListFromWeb()
}