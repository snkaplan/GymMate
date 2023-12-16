package com.gym.gymmate.core.data.service

import com.gym.gymmate.core.data.model.category.CategoryResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface CategoryService {
    @GET("8027470a-3aa9-4b10-8033-b9ccc82b0923")
    suspend fun getExerciseCategories(): List<CategoryResponse>
}
