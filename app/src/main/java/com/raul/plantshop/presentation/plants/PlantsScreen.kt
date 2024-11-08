package com.raul.plantshop.presentation.plants

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.raul.plantshop.R

@Composable
fun PlantsScreen(
    modifier: Modifier = Modifier, viewModel: PlantsViewModel,
    navController: NavController
) {

    Column(
        Modifier.fillMaxSize ().verticalScroll(
            state = rememberScrollState()
        )
    ) {


        HomeHeader() {}
        DiscountBanner(
            modifier = Modifier.weight(0.4f),
            discount = Discount(
                imagePainter = painterResource(R.drawable.plant7),
                rangeDates = "01 - 21 July",
                percetageDiscount = 30
            )
        )
        val uiState =
            viewModel.homeStateFlow.collectAsState(PlantState()).value

        CategoryTabs(selected = uiState.selectedCategory) {
            viewModel.updateCategory(it)
        }

        PlantList(
            plants = uiState.getByCategory(),
            modifier = Modifier
                .weight(1f)

                .padding(start = 10.dp),
            addToCart = { viewModel.addItemToCart(it) },
            onToggleFav = { viewModel.updatePlantFavorite(it.id) },
            onTapCard = {
                navController.navigate(route = "/Details/$it")
            })
        Spacer(modifier = Modifier.padding(top = 10.dp))
    }
}