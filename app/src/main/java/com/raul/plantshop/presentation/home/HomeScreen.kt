package com.raul.plantshop.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.raul.plantshop.presentation.plants.HomeViewModel
import com.raul.plantshop.presentation.plants.PlantList
import com.raul.plantshop.presentation.plants.PlantsScreen
import com.raul.plantshop.presentation.profile.ProfileScreen


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    navController: NavController
) {

    var pageIndex by remember { mutableIntStateOf(0) }
    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController, pageIndex) {
                pageIndex = it
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(
                    state = rememberScrollState()
                )
        ) {

            when (pageIndex) {
                0 -> PlantsScreen(Modifier, viewModel, navController)
                1 -> ProfileScreen(Modifier, navController)
            }

        }


    }

}


