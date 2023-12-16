package com.gym.gymmate.core.domain.category

import com.gym.gymmate.core.data.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {
    operator fun invoke(): Flow<CategoryResult> {
        return flow {
            val result = categoryRepository.getExerciseCategories()
            (result.getOrNull() ?: throw IllegalArgumentException("error message")).also {
                emit(it.toModel())
            }
        }
    }
}