package com.raul.plantshop.data.plant

import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.raul.plantshop.R
import java.io.InputStreamReader

class PlantsApiImpl: PlantsApi {
    override suspend fun loadPlantsResources(resources: Resources): List<PlantData> {
        val inputStream = resources.openRawResource(R.raw.plants)
        val reader = InputStreamReader(inputStream)
        val gson = Gson()
        val type = object : TypeToken<List<PlantData>>() {}.type
        return gson.fromJson(reader, type)
    }
}