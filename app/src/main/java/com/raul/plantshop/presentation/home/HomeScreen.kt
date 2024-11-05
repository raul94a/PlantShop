package com.raul.plantshop.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.raul.plantshop.R


@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    val resources = LocalContext.current.resources
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).verticalScroll(
                state = rememberScrollState()
            )
        ) {
            LaunchedEffect(Lifecycle.State.CREATED) {
                viewModel.loadPlants(resources)
            }
            HomeHeader() {}
            DiscountBanner(

                discount = Discount(
                    imagePainter = painterResource(R.drawable.plant7),
                    rangeDates = "01 - 21 July",
                    percetageDiscount = 30
                )
            )
            CategoryTabs {
                viewModel.updateCategory(it)
            }

            val uiState =
                viewModel.homeStateFlow.collectAsState(HomeState()).value



            PlantList(plants = uiState.getByCategory(), modifier = Modifier.padding(start = 10.dp))
            Spacer(modifier = Modifier.padding(top = 10.dp))

        }
    }
}