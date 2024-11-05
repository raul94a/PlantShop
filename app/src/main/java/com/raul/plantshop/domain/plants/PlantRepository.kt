package com.raul.plantshop.domain.plants

import kotlinx.coroutines.flow.Flow

interface PlantRepository {

    suspend fun loadPlants(): Flow<List<Plant>>
}