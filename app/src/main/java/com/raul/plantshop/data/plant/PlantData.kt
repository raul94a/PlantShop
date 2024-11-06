package com.raul.plantshop.data.plant

import com.raul.plantshop.domain.plants.Plant
import com.raul.plantshop.domain.plants.PlantCategory
import com.raul.plantshop.domain.review.Rating
import com.raul.plantshop.domain.review.Review



data class PlantData(
    val id: String,
    val name: String,
    val description: String,
    val imageUri: String,
    val size: Double,
    val humidity: Int,
    val price: Double,
    val category: String,
    val rating: List<Review>? = emptyList()
)


fun PlantData.toPlant(): Plant {
    val category = if (this.category == "Indoor") {
        PlantCategory.Indoor
    } else {
        PlantCategory.Outdoor
    }
    return Plant(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUri = this.imageUri,
        size = this.size,
        humidity = this.humidity,
        price = this.price,
        category = category,
        rating = Rating(this.rating?.toMutableList() ?: mutableListOf())
    )
}