package com.android.fetch_rewards_2024.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.fetch_rewards_2024.model.FetchListModel
import com.android.fetch_rewards_2024.repo.RepositoryImpl
import com.android.fetch_rewards_2024.source.web.WebResult
import com.android.fetch_rewards_2024.instance.GetListFromLocalInstance
import com.android.fetch_rewards_2024.instance.GetListFromWebInstance
import com.android.fetch_rewards_2024.instance.InsertListToLocalInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SortedLocalViewModel(private val dataRepository: RepositoryImpl): ViewModel() {

    val fetchListLiveDataLocal = MutableLiveData<List<FetchListModel>?>()

    private val getListFromWebInstance get() = GetListFromWebInstance(dataRepository)
    private val insertListToLocalInstance get() = InsertListToLocalInstance(dataRepository)
    private val getListFromLocalInstance get() = GetListFromLocalInstance(dataRepository)

    fun getFetchListFromRemoteAndInsertToLocal() = viewModelScope.launch(Dispatchers.IO){
        when(val remoteResult = getListFromWebInstance.invoke()){

            is WebResult.Success -> {
                val response = remoteResult.data
                insertListToLocalInstance.invoke(response)
            }
            is WebResult.Error -> {
                fetchListLiveDataLocal.postValue(null)
                getFetchListFromLocal()
            }
        }
    }
    fun getFetchListFromLocal() = viewModelScope.launch(Dispatchers.IO) {
        getListFromLocalInstance.invoke().collect{
            if (it.isNotEmpty()) fetchListLiveDataLocal.postValue(it)
            else if (it.isEmpty()) fetchListLiveDataLocal.postValue(emptyList())
            else fetchListLiveDataLocal.postValue(null)
        }
    }
}