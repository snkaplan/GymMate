package com.gym.gymmate.core.data.di

import com.gym.gymmate.core.data.remotedatasource.authentication.AuthenticationRemoteDataSource
import com.gym.gymmate.core.data.remotedatasource.authentication.AuthenticationRemoteDataSourceImpl
import com.gym.gymmate.core.data.repository.authentication.AuthenticationRepository
import com.gym.gymmate.core.data.repository.authentication.AuthenticationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface LoginModule {

    @Binds
    fun bindLoginRemoteDataSource(sourceImpl: AuthenticationRemoteDataSourceImpl): AuthenticationRemoteDataSource

    @Binds
    fun bindAuthRepository(repositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository
}
