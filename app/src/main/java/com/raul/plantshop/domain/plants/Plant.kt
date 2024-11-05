package com.raul.plantshop.domain.plants

import com.raul.plantshop.domain.review.Rating

data class Plant(
    val id: String,
    val name: String,
    val description: String,
    val imageUri: String,
    val size: Double,
    val humidity: Int,
    val price: Double,
    val category: PlantCategory,
    val rating: Rating? = null
)
