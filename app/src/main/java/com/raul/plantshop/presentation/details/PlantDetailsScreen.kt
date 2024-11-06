package com.raul.plantshop.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.raul.plantshop.domain.plants.Plant

@Composable
fun PlantDetailsScreen(modifier: Modifier = Modifier, plant: Plant) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),

        ) { innerPadding ->

        Column(
            modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Hi There ${plant.name}!")
            Image(
                painter = painterResource(plant.getDrawable()), contentDescription = plant.name
            )

        }
    }
}