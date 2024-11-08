package com.raul.plantshop.presentation.plants

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.raul.plantshop.R
import com.raul.plantshop.ui.theme.Typography
import com.raul.plantshop.ui.theme.buttonColor
import com.raul.plantshop.ui.theme.subtitle



@Composable
fun FavoritesScreen(modifier: Modifier = Modifier, viewModel: PlantsViewModel) {
    val uiState by viewModel.homeStateFlow.collectAsState(initial = PlantState())

    val items = uiState.getFavorites()
    if (items.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.no_favs),
                    contentDescription = stringResource(R.string.no_favs),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(250.dp)
                )
                Text(
                    stringResource(R.string.no_favs),
                    style = Typography.bodyLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    } else {


        LazyColumn(

        ) {
            items(count = items.size) { index ->
                val item = items[index]
                key(item.id) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth(1f)
                            .padding(start = 8.dp, end = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,

                            ) {


                            Image(
                                painter = painterResource(item.getDrawable()),
                                contentDescription = item.name,
                                alpha = 0.8f,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(100.dp)
                                    .border(
                                        BorderStroke(2.5.dp, buttonColor), CircleShape
                                    )
                                    .padding(2.5.dp)
                                    .clip(CircleShape)


                            )
                            Column(modifier = Modifier.padding(start = 8.dp)) {
                                Text(
                                    item.name,
                                    style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                                )
                                Text(
                                    item.category.toString(),
                                    modifier = Modifier.padding(top = 8.dp),
                                    style = Typography.bodySmall.copy()
                                )
                            }

                        }
                        IconButton(
                            onClick = {
                                viewModel.updatePlantFavorite(item.id)

                            },
                            modifier = Modifier
                                .border(width = 1.dp, color = subtitle, shape = CircleShape)


                                .padding(0.dp)
                        ) {
                            Icon(
                                imageVector = if (!item.isFav) Icons.Rounded.FavoriteBorder else Icons.Rounded.Favorite,
                                contentDescription = "Search for favorites",
                            )
                        }


                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(
                            horizontal = 15.dp,
                            vertical = 8.dp
                        )
                    )
                }
            }

        }
    }
}