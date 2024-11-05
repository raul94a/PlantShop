package com.raul.plantshop.presentation.home

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raul.plantshop.domain.plants.Plant
import com.raul.plantshop.domain.plants.PlantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val plantRepository: PlantRepository) : ViewModel() {

    var _plants = MutableStateFlow<List<Plant>>(emptyList())

    val plantsFlow = _plants.shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        replay = 1
    )


    fun loadPlants(resources: Resources){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val plants = plantRepository.loadPlantsResources(resources)

                _plants.update { plants }
            }
        }
    }



}