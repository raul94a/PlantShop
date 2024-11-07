package com.raul.plantshop.presentation.plants

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
        viewModel.homeStateFlow.collectAsState(PlantState()).value



    PlantList(
        plants = uiState.getByCategory(),
        modifier = Modifier.padding(start = 10.dp),
        onTapCard = {
            navController.navigate(route = "/Details/$it")
        })
    Spacer(modifier = Modifier.padding(top = 10.dp))
}