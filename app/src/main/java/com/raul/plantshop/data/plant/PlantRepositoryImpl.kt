package com.raul.plantshop.data.plant

import com.raul.plantshop.domain.plants.Plant
import com.raul.plantshop.domain.plants.PlantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PlantRepositoryImpl(private val api: PlantsApi) : PlantRepository {
    override suspend fun loadPlants(): Flow<List<Plant>> {
        val plantsData = api.loadPlants()
        val plants : List<Plant> = plantsData.map { it.toPlant()}

        return flowOf(plants)
    }
}