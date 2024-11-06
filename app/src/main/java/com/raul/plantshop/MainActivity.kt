package com.raul.plantshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.raul.plantshop.data.plant.PlantRepositoryImpl
import com.raul.plantshop.data.plant.PlantsApiImpl
import com.raul.plantshop.presentation.plants.HomeViewModel
import com.raul.plantshop.presentation.shared.PlantsNavGraph
import com.raul.plantshop.ui.theme.PlantShopTheme


sealed class Screens(val route: String) {
    object Home : Screens("/Home")
    object PlantDetails : Screens("/Details")
    object Profile : Screens("/Profile")
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            PlantShopTheme {
                PlantsNavGraph(navController = navController)

            }
        }
    }

}

