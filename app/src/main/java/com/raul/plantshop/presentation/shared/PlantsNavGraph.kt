package com.raul.plantshop.presentation.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.raul.plantshop.data.plant.PlantData
import com.raul.plantshop.data.plant.toPlant
import com.raul.plantshop.presentation.details.PlantDetailsScreen
import com.raul.plantshop.presentation.home.HomeScreen
import com.raul.plantshop.presentation.plants.PlantsViewModel
import com.raul.plantshop.presentation.profile.ProfileScreen
import androidx.navigation.compose.NavHost
import com.raul.plantshop.data.plant.PlantRepositoryImpl
import com.raul.plantshop.data.plant.PlantsApiImpl

@Composable
fun PlantsNavGraph(navController: NavHostController) {
    val api = PlantsApiImpl()
    val repo = PlantRepositoryImpl(api);
    val viewModel = PlantsViewModel(repo)
    NavHost(navController = navController, startDestination = "/Home") {
        addPlantScreen(navController,viewModel)
        addPlantDetails(navController,viewModel)
    }

}


fun NavGraphBuilder.addPlantScreen(navController: NavController,viewModel: PlantsViewModel) {

    composable(route = "/Home") {
        var initialApiCalled by rememberSaveable { mutableStateOf(false) }
        if (!initialApiCalled) {
            val resources = LocalContext.current.resources
            LaunchedEffect(Unit) {
                viewModel.loadPlants(resources)
                initialApiCalled = true
            }
        }
        HomeScreen(viewModel = viewModel, navController = navController)
    }
}

fun NavGraphBuilder.addPlantDetails(navController: NavController, viewModel: PlantsViewModel) {
    composable(route = "/Details/{data}", arguments = listOf(navArgument("data") {
        type = NavType.StringType
    })) { navBackStackEntry ->
        val data = navBackStackEntry.arguments?.getString("data")
        println("data: ${data?.javaClass}")
        if (data == null) {
            navController.navigateUp()
        }

        val gson = Gson()
        val plant = gson.fromJson(data, PlantData::class.java)
        PlantDetailsScreen(plant = plant.toPlant(), navController = navController,viewModel = viewModel)
    }
}

