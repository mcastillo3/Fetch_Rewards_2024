package com.android.fetch_rewards_2024.di

import android.app.Application
import androidx.room.Room
import com.android.fetch_rewards_2024.repo.RepositoryImpl
import com.android.fetch_rewards_2024.source.local.AppDb
import com.android.fetch_rewards_2024.source.local.FetchListDao
import com.android.fetch_rewards_2024.source.local.LocalSourceImpl
import com.android.fetch_rewards_2024.source.web.FetchListApi
import com.android.fetch_rewards_2024.source.web.WebSourceImpl
import com.android.fetch_rewards_2024.utils.Constants.BASE_URL
import com.android.fetch_rewards_2024.vm.SortedLocalViewModel
import com.android.fetch_rewards_2024.vm.SortedRestViewModel
import com.android.fetch_rewards_2024.vm.UnsortedViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// networkModule.kt
val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), BASE_URL) }
    factory { provideApi<FetchListApi>(get()) }
}

fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder().build()

fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

inline fun <reified T> provideApi(retrofit: Retrofit): T =
    retrofit.create(T::class.java)

// dataSourceModule.kt
val dataSourceModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
    single { provideDataRepository(get(), get()) }
}

fun provideDatabase(app: Application): AppDb =
    Room.databaseBuilder(app, AppDb::class.java, "fetchDatabase")
        .fallbackToDestructiveMigration()
        .build()

fun provideDao(database: AppDb): FetchListDao? =
    database.fetchListDao()

fun provideDataRepository(api: FetchListApi, dao: FetchListDao): RepositoryImpl =
    RepositoryImpl(WebSourceImpl(api), LocalSourceImpl(dao))

// viewModelModule.kt
val viewModelModule = module {
    viewModel { SortedRestViewModel(get()) }
    viewModel { SortedLocalViewModel(get()) }
    viewModel { UnsortedViewModel(get()) }
}

// koinModules.kt - Aggregate all modules
val appModules = listOf(networkModule, dataSourceModule, viewModelModule)