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

class SortedRestViewModel(private val dataRepository: RepositoryImpl): ViewModel() {

    val fetchListLiveDataRemote = MutableLiveData<List<FetchListModel>?>()
    private val getListFromWebInstance get() = GetListFromWebInstance(dataRepository)

    fun getFetchListFromRemoteAndSort() = viewModelScope.launch(Dispatchers.IO){

        when(val remoteResult = getListFromWebInstance.invoke()){

            is WebResult.Success -> {
                val response = remoteResult.data
                var sortedList = response.filter { !it.name.isNullOrBlank() }
                sortedList = sortedList.sortedWith(object : Comparator<FetchListModel> {
                    override fun compare(o1: FetchListModel, o2: FetchListModel) =
                        extractInt(o1) - extractInt(o2)

                    fun extractInt(s: FetchListModel): Int {
                        val num = s.name!!.replace("\\D".toRegex(), "")
                        return if (num.isEmpty()) 0 else Integer.parseInt(num)
                    }
                })
                sortedList = sortedList.sortedBy { it.listId }

                fetchListLiveDataRemote.postValue(sortedList)
            }
            is WebResult.Error -> fetchListLiveDataRemote.postValue(null)
        }
    }
}