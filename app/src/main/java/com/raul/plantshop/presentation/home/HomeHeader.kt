package com.raul.plantshop.presentation.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raul.plantshop.R
import com.raul.plantshop.ui.theme.Typography
import com.raul.plantshop.ui.theme.mainText
import com.raul.plantshop.ui.theme.subtitle


@Composable
fun HomeHeader(modifier: Modifier? = null, onClickFavorites: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()

            .padding(horizontal = 15.dp)
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {


        Text(
            stringResource(R.string.fav_plants),

            style = Typography.titleLarge,
            minLines = 2,
            maxLines = 2,
            modifier = Modifier.fillMaxWidth(0.5f)

        )

        IconButton(
            onClick = onClickFavorites,
            modifier = Modifier
                .border(width = 1.dp, color = subtitle, shape = CircleShape)


                .padding(0.dp)
        ) {
            Icon(imageVector = Icons.Rounded.FavoriteBorder, contentDescription = "Search for favorites")
        }
    }
}