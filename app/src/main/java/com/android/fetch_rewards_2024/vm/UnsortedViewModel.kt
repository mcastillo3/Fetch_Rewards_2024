package com.android.fetch_rewards_2024.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.fetch_rewards_2024.model.FetchListModel
import com.android.fetch_rewards_2024.repo.RepositoryImpl
import com.android.fetch_rewards_2024.source.web.WebResult
import com.android.fetch_rewards_2024.instance.GetListFromWebInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UnsortedViewModel(private val dataRepository: RepositoryImpl): ViewModel() {
    val fetchListLiveDataUnsorted = MutableLiveData<List<FetchListModel>?>()
    private val getListFromWebInstance get() = GetListFromWebInstance(dataRepository)

    fun getFetchListFromRemote() = viewModelScope.launch(Dispatchers.IO){

        when(val remoteResult = getListFromWebInstance.invoke()){

            is WebResult.Success -> {
                val response = remoteResult.data
                fetchListLiveDataUnsorted.postValue(response)
            }
            is WebResult.Error -> fetchListLiveDataUnsorted.postValue(null)
        }
    }
}