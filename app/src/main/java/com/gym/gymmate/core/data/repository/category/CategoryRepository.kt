package com.gym.gymmate.core.data.repository.category

import com.gym.gymmate.core.data.model.category.CategoryResponse

interface CategoryRepository {
    suspend fun getExerciseCategories(): Result<List<CategoryResponse>>
}