package com.android.fetch_rewards_2024.source.web

import java.lang.Exception

sealed class WebResult<out T> {

    data class Success<out T>(val data: T):WebResult<T>()
    data class Error(val exception: Exception): WebResult<Nothing>()

    override fun toString(): String {
        return when(this){
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}