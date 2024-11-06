package com.raul.plantshop.presentation.plants


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raul.plantshop.R
import com.raul.plantshop.ui.theme.Typography
import com.raul.plantshop.ui.theme.bannerGreen
import com.raul.plantshop.ui.theme.subtitle


data class Discount(
    val imagePainter: Painter, val percetageDiscount: Int, val rangeDates: String
)

@Composable
fun DiscountBanner(discount: Discount) {

    val bannerHeight = remember { 175.dp }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(bannerHeight)
            .padding(horizontal = 15.dp).padding(bottom = 10.dp)


    ) {


        Box(
            modifier = Modifier
                .padding(top = 25.dp)
                .fillMaxSize()

                .background(color = bannerGreen, shape = RoundedCornerShape(18.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(), verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${discount.percetageDiscount}% " + stringResource(R.string.discount),
                    style = Typography.titleLarge.copy(fontSize = 26.sp),
                    modifier = Modifier.padding(bottom = 10.dp),


                    )
                Text(
                    discount.rangeDates, modifier = Modifier,
                    style = Typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = subtitle
                    )
                )

            }


        }
        Image(
            painter = discount.imagePainter,
            contentDescription = "Discount Image Banner",
            contentScale = ContentScale.Fit,

            alignment = Alignment.BottomEnd,
            modifier = Modifier
                .align(
                    Alignment.TopEnd
                )
                .padding(start = 20.dp, bottom = 20.dp)
                .size(bannerHeight)


        )


    }


}