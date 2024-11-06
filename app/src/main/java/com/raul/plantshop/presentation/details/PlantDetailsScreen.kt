package com.raul.plantshop.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.raul.plantshop.R
import com.raul.plantshop.domain.plants.Plant
import com.raul.plantshop.ui.theme.Typography
import com.raul.plantshop.ui.theme.buttonColor
import com.raul.plantshop.ui.theme.headerGrey
import com.raul.plantshop.ui.theme.mainBlack
import com.raul.plantshop.ui.theme.subtitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantDetailsScreen(modifier: Modifier = Modifier, plant: Plant, navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors().copy(

                ),
                modifier = Modifier
                    .fillMaxWidth(),
                title = {
                    DetailsAppBarRow(
                        modifier = Modifier,
                        isFav = plant.isFav,
                        onToggleFavs = {},
                        onNavigateBack = {
                            navController.navigateUp()
                        }
                    )

                })
        }
    ) { innerPadding ->

        Column(
            modifier
                .padding(innerPadding)
                .fillMaxSize()
             ,
            verticalArrangement = Arrangement.SpaceBetween,
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f),
                alignment = Alignment.BottomCenter,
                painter = painterResource(plant.getDrawable()), contentDescription = plant.name
            )
            DetailsTable(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                   ,
                plant = plant,
            )


        }
    }
}

@Composable
fun DetailsAppBarRow(
    modifier: Modifier = Modifier,
    isFav: Boolean,
    onNavigateBack: () -> Unit,
    onToggleFavs: () -> Unit
) {
    var favorite by remember { mutableStateOf(isFav) }
    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp).padding(end = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = onNavigateBack,
            modifier = Modifier
                .border(width = 1.dp, color = subtitle, shape = CircleShape)
                .padding(0.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = "Search for favorites"
            )
        }
        Text(
            stringResource(R.string.details),
            style = Typography.bodyMedium.copy(
                fontWeight = FontWeight.Black,
                fontSize = 18.sp
            )
        )

        IconButton(
            onClick = {
                onToggleFavs()
                favorite = !favorite
                println("Favo: $favorite")
            },
            modifier = Modifier
                .border(width = 1.dp, color = subtitle, shape = CircleShape)


                .padding(0.dp)
        ) {
            Icon(
                imageVector = if (!favorite) Icons.Rounded.FavoriteBorder else Icons.Rounded.Favorite,
                contentDescription = "Search for favorites",
            )
        }
    }
}