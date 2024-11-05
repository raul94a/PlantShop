package com.raul.plantshop.data.plant

import com.raul.plantshop.domain.plants.Plant
import com.raul.plantshop.domain.plants.PlantCategory
import com.raul.plantshop.domain.review.Rating

data class PlantData(
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


fun PlantData.toPlant(): Plant {
    return Plant(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUri = this.imageUri,
        size = this.size,
        humidity = this.humidity,
        price = this.price,
        category = this.category,
        rating = this.rating
    )
}