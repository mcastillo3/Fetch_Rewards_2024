package com.android.fetch_rewards_2024.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.fetch_rewards_2024.model.FetchListModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FetchListDao {

    @Query("SELECT * FROM fetchList WHERE name <> '' AND name IS NOT NULL ORDER BY listId, cast(name as unsigned) ASC")
    fun getList(): Flow<List<FetchListModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: List<FetchListModel>)
}