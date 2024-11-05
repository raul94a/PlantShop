package com.raul.plantshop.domain.plants

import android.content.res.Resources
import kotlinx.coroutines.flow.Flow

interface PlantRepository {

    suspend fun loadPlantsResources(resources: Resources): List<Plant>
}