package com.raul.plantshop.presentation.details

import android.telecom.Call.Details
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raul.plantshop.R
import com.raul.plantshop.domain.plants.Plant
import com.raul.plantshop.ui.theme.Typography
import com.raul.plantshop.ui.theme.buttonColor
import com.raul.plantshop.ui.theme.headerGrey
import com.raul.plantshop.ui.theme.mainBlack
import com.raul.plantshop.ui.theme.mainText
import com.raul.plantshop.ui.theme.subtitle

data class DetailsColumn(val title: String, val value: String)

@Composable
fun DetailsTable(modifier: Modifier = Modifier, plant: Plant, onAddToCart: (Plant) -> Unit) {
    val columns = listOf(
        DetailsColumn(
            stringResource(R.string.size),
            plant.size.toString()
        ),
        DetailsColumn(
            stringResource(R.string.plant),
            plant.name
        ),
        DetailsColumn(
            stringResource(R.string.height),
            plant.size.toString()
        ),
        DetailsColumn(
            stringResource(R.string.humidity),
            plant.humidity.toString() + "%"
        )
    )
    Column(
        modifier = modifier.padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val isRatingsEmpty = plant.rating?.reviews.isNullOrEmpty()
            Text(
                plant.name,
                style = Typography.titleMedium,
                modifier = Modifier.fillMaxWidth(if (isRatingsEmpty) 1f else 0.35f),
                overflow = if (isRatingsEmpty) TextOverflow.Visible else TextOverflow.Ellipsis,
                maxLines = 1
            )
            if (!isRatingsEmpty) {
                Row(verticalAlignment = Alignment.CenterVertically) {


                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "reviews stars",
                        tint = buttonColor

                        )
                    Text(
                        plant.rating!!.getRatingMean().toString(),
                        modifier = Modifier.padding(horizontal = 10.dp),
                        style = Typography.bodySmall,
                    )
                    Text(

                        "(${plant.rating.reviews.size} ${stringResource(R.string.reviews)})",
                        maxLines = 1,
                        style = Typography.bodySmall,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(plant.description, style = Typography.bodyMedium)
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            columns.forEachIndexed { i, column ->

                Column {
                    Text(
                        column.title,
                        style = Typography.bodySmall.copy(
                            color = headerGrey,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        column.value,
                        style = Typography.bodyMedium.copy(
                            color = mainBlack,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    stringResource(R.string.price),
                    style = Typography.bodySmall.copy(
                        color = headerGrey,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    "$" + plant.price.toString(),
                    style = Typography.bodyMedium.copy(
                        color = mainBlack,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            OutlinedButton(
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = buttonColor
                ),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(0.dp, Color.Transparent),
                onClick = {
                    onAddToCart(plant)
                }) {
                Text(
                    stringResource(R.string.add_cart),
                    style = Typography.bodyMedium.copy(
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }


    }
}