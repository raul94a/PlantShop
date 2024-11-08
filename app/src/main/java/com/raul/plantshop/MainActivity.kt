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

    private val paymentDataLauncher = registerForActivityResult(TaskResultContracts.GetPaymentDataResult()) { taskResult ->
        when (taskResult.status.statusCode) {
            CommonStatusCodes.SUCCESS -> {
                taskResult.result!!.let {
                    Log.i("Google Pay result:", it.toJson())
                    model.setPaymentData(it)
                }
            }
            CommonStatusCodes.CANCELED -> Log.e("ERROR", "${taskResult.result}")
            AutoResolveHelper.RESULT_ERROR ->  Log.e("ERROR", "${taskResult.result}")
            CommonStatusCodes.INTERNAL_ERROR -> Log.e("ERROR", "${taskResult.result}")
        }
    }
    private val model: CheckoutViewModel by viewModels()

    private fun requestPayment() {
        val task = model.getLoadPaymentDataTask(priceCents = 1000L)
        task.addOnCompleteListener(paymentDataLauncher::launch)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            PlantShopTheme {
                PlantsNavGraph(navController = navController, model, onRequestPayment = {
                    requestPayment()
                } )

            }
        }
    }

}

lateinit var requestPaymentInjector :  Unit