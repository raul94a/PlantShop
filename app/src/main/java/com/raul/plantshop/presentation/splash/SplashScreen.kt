package com.raul.plantshop.presentation.splash

import android.graphics.Paint.Align
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raul.plantshop.R
import com.raul.plantshop.ui.theme.Typography
import com.raul.plantshop.ui.theme.buttonColor
import com.raul.plantshop.ui.theme.subtitle


@Composable
fun SplashScreen(modifier: Modifier = Modifier, onContinue: () -> Unit) {

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.Start,


            ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .padding(top = 30.dp)
                    .height(150.dp),


                ) {

                Text(
                    stringResource(R.string.app_name),
                    modifier = Modifier
                        .rotate(-90f)
                        .padding(end = 75.dp)

                )

                VerticalDivider(
                    thickness = 1.5.dp,
                    color = subtitle,
                    modifier = Modifier.padding(start = 100.dp)
                )
                Text(
                    stringResource(R.string.plant_tree),
                    style = Typography.titleLarge.copy(
                        fontSize = 42.sp,
                        lineHeight = 50.sp,
                        lineBreak = LineBreak.Heading,
                    ),
                    minLines = 3,
                    textAlign = TextAlign.Left,
                    modifier = Modifier

                        .padding(start = 125.dp)

                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .padding(15.dp)
                    .padding(vertical = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(R.drawable.tree),
                        contentScale = ContentScale.Fit,
                        contentDescription = ""
                    )
                    Text(
                        stringResource(R.string.worldwide_delivery),
                        textAlign = TextAlign.Center,
                        style = Typography.bodyMedium.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.width(250.dp).padding(top = 20.dp),

                    )
                }

                TextButton(
                    modifier = Modifier.size(60.dp),
                    onClick = onContinue,
                    border = BorderStroke(0.dp, Color.Transparent),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors().copy(containerColor = buttonColor)
                ) {
                    Text(
                        stringResource(R.string.go),
                        style = Typography.bodyMedium.copy(color = Color.White)
                    )
                }
            }


        }
    }
}