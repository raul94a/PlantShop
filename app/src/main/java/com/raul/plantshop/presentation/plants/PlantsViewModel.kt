package com.raul.plantshop.presentation.plants

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raul.plantshop.domain.cart.PlantItem
import com.raul.plantshop.domain.cart.ShoppingCart
import com.raul.plantshop.domain.plants.Plant
import com.raul.plantshop.domain.plants.PlantCategory
import com.raul.plantshop.domain.plants.PlantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class PlantState(
    val plants: List<Plant> = emptyList(),
    val selectedCategory: PlantCategory = PlantCategory.All,
    val shoppingCart: ShoppingCart = ShoppingCart(mutableMapOf())
) {
    fun getByCategory(): List<Plant>{

        return when(selectedCategory){
            PlantCategory.All -> plants
            PlantCategory.Indoor -> plants.filter { it.category == selectedCategory }
            PlantCategory.Outdoor -> plants.filter { it.category == selectedCategory }
            PlantCategory.Popular -> calculatePopulars()
        }



    }

    private fun calculatePopulars() : List<Plant>{
        val sortedPlants = plants.sortedByDescending { it.rating?.reviews?.size }
        val length = plants.size
        val endIndex = if(length >= 5) 5 else length
        return sortedPlants.subList(0,endIndex)
    }
}


class PlantsViewModel(private val plantRepository: PlantRepository) : ViewModel() {

    private var _homeState = MutableStateFlow(PlantState())

    val homeStateFlow = _homeState.shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        replay = 1
    )


    fun loadPlants(resources: Resources){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val plants = plantRepository.loadPlantsResources(resources)
                _homeState.update {
                    it.copy(
                        plants = plants.shuffled()
                    )
                }
            }
        }
    }

    fun updateCategory(category: PlantCategory) {
        _homeState.update { it.copy(selectedCategory = category) }
    }

    fun updatePlantFavorite(id: String) {
        _homeState.update { currentState ->
            val updatedPlants = currentState.plants.map { plant ->
                if (plant.id == id) {
                    plant.copy(isFav = !plant.isFav)
                } else {
                    plant
                }
            }
            currentState.copy(plants = updatedPlants)
        }
    }

    fun addItemToCart(plant: Plant) {
        _homeState.update {
            val cart = it.shoppingCart
            cart.addItem(plant)
            val items = cart.items
            it.copy(shoppingCart = ShoppingCart(items))
        }
    }

    fun removeItemFromCart(plant: Plant) {
        _homeState.update {
            val cart = it.shoppingCart
            cart.removeItem(plant)
            val items = cart.items
            it.copy(shoppingCart = ShoppingCart(items))
        }
    }




}