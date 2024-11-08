package com.raul.plantshop.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.raul.plantshop.presentation.plants.PlantsViewModel
import com.raul.plantshop.presentation.plants.PlantsScreen
import com.raul.plantshop.presentation.plantsCart.CheckoutViewModel
import com.raul.plantshop.presentation.plantsCart.PlantsCart
import com.raul.plantshop.presentation.profile.ProfileScreen


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: PlantsViewModel,
    navController: NavController,
    checkoutViewModel: CheckoutViewModel,
    onRequestPayment: () -> Unit
) {


    var pageIndex by remember { mutableIntStateOf(0) }

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .fillMaxHeight(1f),
        bottomBar = {
            BottomNavigationBar(navController, pageIndex, viewModel) {
                pageIndex = it
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .fillMaxHeight(1f)
                .padding(innerPadding)


        ) {

            when (pageIndex) {
                0 -> PlantsScreen(Modifier, viewModel, navController)
                1 -> Text("hola")
                2 -> {
                    val uiState =
                        checkoutViewModel.paymentUiState.collectAsStateWithLifecycle().value

                    PlantsCart(Modifier, viewModel, navController, uiState, onRequestPayment)
                }

                3 -> ProfileScreen(Modifier, navController)
            }

        }


    }

}


