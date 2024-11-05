package com.raul.plantshop.data.plant




interface PlantsApi {

    suspend fun loadPlants(): List<PlantData>;
}