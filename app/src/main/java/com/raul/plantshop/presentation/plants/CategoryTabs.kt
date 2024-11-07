package com.raul.plantshop.presentation.plants

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.raul.plantshop.R
import com.raul.plantshop.domain.plants.PlantCategory
import com.raul.plantshop.ui.theme.Typography
import com.raul.plantshop.ui.theme.mainText


@Composable
fun CategoryTabs(modifier: Modifier = Modifier,
                 selected: PlantCategory,
                 onSelectCategory: (PlantCategory) -> Unit) {


    println("SelectedCategory: $selected")
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .horizontalScroll(
                scrollState
            )
            .padding(horizontal = 15.dp)
    ) {
        ElevatedButton(
            modifier = Modifier.padding(end = 10.dp),
            colors = ButtonDefaults.buttonColors()
                .copy(containerColor = if (selected == PlantCategory.All) mainText else Color.White),
            onClick = {
                onSelectCategory(PlantCategory.All)

            }) {
            Text(
                stringResource(R.string.all),
                style = Typography.bodyMedium,
                color = if (selected == PlantCategory.All) Color.White else mainText
            )
        }
        ElevatedButton(modifier = Modifier.padding(end = 10.dp),
            colors = ButtonDefaults.buttonColors()
                .copy(containerColor = if (selected == PlantCategory.Indoor) mainText else Color.White),

            onClick = {


                onSelectCategory(PlantCategory.Indoor)
            }) {
            Text(
                stringResource(R.string.indoor),
                style = Typography.bodyMedium,
                color = if (selected == PlantCategory.Indoor) Color.White else mainText

            )
        }
        ElevatedButton(modifier = Modifier.padding(end = 10.dp),
            colors = ButtonDefaults.buttonColors()
                .copy(containerColor = if (selected == PlantCategory.Outdoor) mainText else Color.White),
            onClick = {


                onSelectCategory(PlantCategory.Outdoor)
            }) {
            Text(
                stringResource(R.string.outdoor),
                style = Typography.bodyMedium,

                color = if (selected == PlantCategory.Outdoor) Color.White else mainText
            )
        }
        ElevatedButton(modifier = Modifier.padding(end = 10.dp),
            colors = ButtonDefaults.buttonColors()
                .copy(

                    containerColor = if (selected == PlantCategory.Popular) mainText else Color.White
                ),
            onClick = {
                onSelectCategory(PlantCategory.Popular)
            }) {
            Text(
                stringResource(R.string.popular),
                style = Typography.bodyMedium,

                color = if (selected == PlantCategory.Popular) Color.White else mainText
            )
        }
    }

}