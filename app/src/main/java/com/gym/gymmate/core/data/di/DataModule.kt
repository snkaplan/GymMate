package com.gym.gymmate.core.data.di

import com.gym.gymmate.core.data.util.ConnectivityManager
import com.gym.gymmate.core.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManager,
    ): NetworkMonitor
}