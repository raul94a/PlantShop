package com.raul.plantshop.presentation.plants


import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raul.plantshop.R
import com.raul.plantshop.ui.theme.Typography
import com.raul.plantshop.ui.theme.bannerGreen
import com.raul.plantshop.ui.theme.buttonColor
import com.raul.plantshop.ui.theme.headerGrey
import com.raul.plantshop.ui.theme.mainBlack
import com.raul.plantshop.ui.theme.subtitle


data class Discount(
    val imagePainter: Painter, val percetageDiscount: Int, val rangeDates: String
)

@Composable
fun DiscountBanner(modifier: Modifier, discount: Discount) {

    val bannerHeight = remember { 175.dp }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(bannerHeight)
            .padding(horizontal = 15.dp)
            .padding(bottom = 10.dp)
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = Color.Black.copy(alpha = 0.52f),
                ambientColor = Color.Black.copy(alpha = 0.1f)
            )


    ) {


        Box(
            modifier = Modifier
                .padding(top = 25.dp)
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(listOf(bannerGreen, Color.White)),
                    shape = RoundedCornerShape(18.dp)

                )


        ) {

            }
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
                        color = headerGrey
                    )
                )

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

