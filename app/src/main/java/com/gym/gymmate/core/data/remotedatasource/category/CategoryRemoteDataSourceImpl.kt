package com.gym.gymmate.core.data.remotedatasource.category

import com.gym.gymmate.core.data.model.category.CategoryResponse
import com.gym.gymmate.core.data.service.CategoryService
import javax.inject.Inject

class CategoryRemoteDataSourceImpl @Inject constructor(private val categoryService: CategoryService) :
    CategoryRemoteDataSource {
    override suspend fun getExerciseCategories(): Result<List<CategoryResponse>> {
        return runCatching {
            categoryService.getExerciseCategories()
        }
    }
}