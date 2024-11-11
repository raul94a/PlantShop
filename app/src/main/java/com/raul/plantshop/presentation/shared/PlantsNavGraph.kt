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
import androidx.navigation.compose.NavHost
import com.raul.plantshop.Screens
import com.raul.plantshop.data.plant.PlantRepositoryImpl
import com.raul.plantshop.data.plant.PlantsApiImpl
import com.raul.plantshop.presentation.plantsCart.CheckoutViewModel
import com.raul.plantshop.presentation.splash.SplashScreen

@Composable
fun PlantsNavGraph(
    navController: NavHostController,
    model: CheckoutViewModel
) {
    val api = PlantsApiImpl()
    val repo = PlantRepositoryImpl(api);
    val viewModel = PlantsViewModel(repo)

    NavHost(navController = navController, startDestination = Screens.Splash.route) {
        addSplash(navController)
        addPlantScreen(navController, viewModel, model)
        addPlantDetails(navController, viewModel)
    }

}

fun NavGraphBuilder.addSplash(navController: NavController) {
    composable(route = Screens.Splash.route) {
        SplashScreen() {
            navController.navigate(Screens.Home.route)
        }
    }
}

fun NavGraphBuilder.addPlantScreen(
    navController: NavController,
    viewModel: PlantsViewModel,
    checkoutViewModel: CheckoutViewModel
) {

    composable(route = "/Home") {
        var initialApiCalled by rememberSaveable { mutableStateOf(false) }
        if (!initialApiCalled) {
            val resources = LocalContext.current.resources
            LaunchedEffect(Unit) {
                viewModel.loadPlants(resources)
                initialApiCalled = true
            }
        }
        HomeScreen(
            viewModel = viewModel,
            checkoutViewModel = checkoutViewModel,
            navController = navController
        )
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
        PlantDetailsScreen(
            plant = plant.toPlant(),
            navController = navController,
            viewModel = viewModel
        )
    }
}

