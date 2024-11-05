package com.raul.plantshop.data.plant

import android.content.res.Resources
import com.raul.plantshop.domain.plants.Plant
import com.raul.plantshop.domain.plants.PlantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PlantRepositoryImpl(private val api: PlantsApi) : PlantRepository {
    override suspend fun loadPlantsResources(resources: Resources): List<Plant> {
        val plantsData = api.loadPlantsResources(resources)
        val plants : List<Plant> = plantsData.map { it.toPlant()}

        return plants
    }
}