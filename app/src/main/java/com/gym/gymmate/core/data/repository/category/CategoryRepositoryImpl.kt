package com.gym.gymmate.core.data.repository.category

import com.gym.gymmate.core.data.model.category.CategoryResponse
import com.gym.gymmate.core.data.remotedatasource.category.CategoryRemoteDataSource
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val categoryRemoteDataSource: CategoryRemoteDataSource) :
    CategoryRepository {
    override suspend fun getExerciseCategories(): Result<List<CategoryResponse>> {
        return categoryRemoteDataSource.getExerciseCategories()
    }
}