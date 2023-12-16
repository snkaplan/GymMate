package com.gym.gymmate.core.domain.category

import com.gym.gymmate.core.data.model.category.CategoryResponse

data class CategoryResult(val categoryItems: List<CategoryItem>)

data class CategoryItem(val id: String, val title: String, val imageResource: String)

fun List<CategoryResponse>.toModel(): CategoryResult {
    return CategoryResult(map {
        CategoryItem(it.categoryId, it.categoryTitle.orEmpty(), it.categoryImageSource.orEmpty())
    })
}
