package com.gym.gymmate.core.data.di

import com.gym.gymmate.core.data.remotedatasource.category.CategoryRemoteDataSource
import com.gym.gymmate.core.data.remotedatasource.category.CategoryRemoteDataSourceImpl
import com.gym.gymmate.core.data.repository.category.CategoryRepository
import com.gym.gymmate.core.data.repository.category.CategoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface CategoryModule {

    @Binds
    fun bindCategoryRemoteDataSource(sourceImpl: CategoryRemoteDataSourceImpl): CategoryRemoteDataSource

    @Binds
    fun bindAuthRepository(repositoryImpl: CategoryRepositoryImpl): CategoryRepository
}
