package com.raul.plantshop

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.wallet.AutoResolveHelper
import com.google.android.gms.wallet.contract.TaskResultContracts
import com.raul.plantshop.presentation.plantsCart.CheckoutViewModel
import com.raul.plantshop.presentation.shared.PlantsNavGraph
import com.raul.plantshop.ui.theme.PlantShopTheme


sealed class Screens(val route: String) {
    object Home : Screens("/Home")
    object PlantDetails : Screens("/Details")
    object Profile : Screens("/Profile")
    object Cart: Screens("/Cart")
    object Favs: Screens("/Fav")
}


class MainActivity : ComponentActivity() {
    private val model: CheckoutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            PlantShopTheme {
                PlantsNavGraph(navController = navController, model)

            }
        }
    }

}

