package com.raul.plantshop.data.plant

import android.content.res.Resources


interface PlantsApi {

    suspend fun loadPlantsResources(resources: Resources): List<PlantData>;
}