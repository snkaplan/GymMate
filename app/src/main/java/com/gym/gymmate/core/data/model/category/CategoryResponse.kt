package com.gym.gymmate.core.data.model.category

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("id")
    val categoryId: String,
    @SerializedName("name")
    val categoryTitle: String?,
    @SerializedName("image")
    val categoryImageSource: String?
)
