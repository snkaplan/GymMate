package com.gym.gymmate.core.data.remotedatasource.category

import com.gym.gymmate.core.data.model.category.CategoryResponse

interface CategoryRemoteDataSource {
    suspend fun getExerciseCategories(): Result<List<CategoryResponse>>
}