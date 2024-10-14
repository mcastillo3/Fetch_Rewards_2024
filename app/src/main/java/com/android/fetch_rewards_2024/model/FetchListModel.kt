package com.android.fetch_rewards_2024.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "fetchList", indices = [Index(value = ["id"], unique = true)])
data class FetchListModel(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "listId")
    val listId: Int?,
    @ColumnInfo(name = "name")
    val name: String?
)