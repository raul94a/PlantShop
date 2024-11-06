package com.raul.plantshop.domain.plants

import com.raul.plantshop.R
import com.raul.plantshop.data.plant.PlantData
import com.raul.plantshop.data.plant.toPlant
import com.raul.plantshop.domain.review.Rating
import kotlinx.serialization.Serializable

@Serializable
data class Plant(
    val id: String,
    val name: String,
    val isFav : Boolean = false,
    val description: String,
    val imageUri: String,
    val size: Double,
    val humidity: Int,
    val price: Double,
    val category: PlantCategory,
    val rating: Rating? = null
) {
    fun getDrawable(): Int {

        if (
            id == "1"
        ) {
            return R.drawable.plant1
        } else if (id == "2") {
            return R.drawable.plant2
        } else if (id == "3") {
            return R.drawable.plant3
        } else if (id == "4") {
            return R.drawable.plant4
        } else if (id == "5") {
            return R.drawable.plant5
        } else if (id == "6") {
            return R.drawable.plant6
        } else if (id == "7") {
            return R.drawable.plant7
        } else if (id == "8") {
            return R.drawable.plant8
        } else if (id == "9") {
            return R.drawable.plant9
        } else if (id == "10") {
            return R.drawable.plant10
        } else if (id == "11") {
            return R.drawable.plant11
        } else if (id == "12") {
            return R.drawable.plant12
        } else if (id == "13") {
            return R.drawable.plant13
        } else if (id == "14") {
            return R.drawable.plant14
        } else {
            return R.drawable.plant15
        }
    }


}

fun Plant.toPlantData() : PlantData {
    val category = if (this.category == PlantCategory.Indoor) {
        "Indoor"
    } else {
        "Outdoor"
    }
    return PlantData(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUri = this.imageUri,
        size = this.size,
        humidity = this.humidity,
        price = this.price,
        category = category,
        rating = this.rating?.reviews ?: emptyList()
    )
}
