package com.raul.plantshop.presentation.plants

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.raul.plantshop.R
import com.raul.plantshop.domain.plants.Plant
import com.raul.plantshop.domain.plants.toPlantData
import com.raul.plantshop.ui.theme.Typography
import com.raul.plantshop.ui.theme.cardContentColor
import com.raul.plantshop.ui.theme.mainText

@Composable
fun PlantList(modifier: Modifier = Modifier, plants: List<Plant>, onTapCard: (String) -> Unit) {
    LazyRow(
        modifier = modifier
            .fillMaxSize(1f)
            .padding(horizontal = 10.dp)
            .padding(top = 5.dp),

        horizontalArrangement = Arrangement.spacedBy(10.dp),

        ) {
        items(plants.size) { i ->
            val plant = plants[i]
            key(plant.id) {
                PlantItem(plant = plant){
                    onTapCard(it)
                }
            }

        }
    }
}

@Composable
fun PlantItem(modifier: Modifier = Modifier, plant: Plant, onTapCard: (String) -> Unit) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                val gson = Gson()
                val plantJson = gson.toJson(plant.toPlantData())
                onTapCard(plantJson)
            }
            .height(380.dp)
            .width(280.dp)
            .padding(top = 10.dp)
            .background(
                color = cardContentColor,
                shape = RoundedCornerShape(12.dp)
            ),
    ) {


        Image(
            painter = painterResource(plant.getDrawable()),
            modifier = Modifier
                .fillMaxSize(.9f)
                .align(Alignment.CenterEnd),
            contentScale = ContentScale.Fit,
            contentDescription = ""
        )


        Text(
            "$" + plant.price,
            style = Typography.titleMedium,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp).padding(end = 5.dp)
        )
        Row(

            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(horizontal = 5.dp)
                .padding(bottom = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ElevatedButton(
                modifier = Modifier,

                onClick = {
                    //AddToCart
                }) {
                Text(
                    stringResource(R.string.add_cart),
                    color = mainText,
                    style = Typography.bodyMedium.copy(fontWeight = FontWeight.Bold),

                    )
            }
            IconButton(
                modifier = Modifier
                    .padding(start = 5.dp)


                    .background(color = mainText, shape = CircleShape),
                onClick = {
                    // add favorite
                }) {
                Image(

                    imageVector = Icons.Rounded.FavoriteBorder,
                    colorFilter = ColorFilter.tint(color = Color.White),
                    contentDescription = ""
                )

            }

        }

        Text(
            plant.name,
            Modifier
                .align(Alignment.TopStart)
                .padding(top = 35.dp, start = 10.dp),

            style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 25.sp),

            )
    }
}