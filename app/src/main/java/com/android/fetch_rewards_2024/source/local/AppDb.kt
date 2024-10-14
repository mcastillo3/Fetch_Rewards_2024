package com.android.fetch_rewards_2024.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.fetch_rewards_2024.model.FetchListModel

@Database(entities = [FetchListModel::class], version = 3, exportSchema = false)
abstract class AppDb: RoomDatabase() {

    abstract fun fetchListDao(): FetchListDao?
}