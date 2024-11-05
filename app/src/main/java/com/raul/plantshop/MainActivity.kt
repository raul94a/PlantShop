package com.raul.plantshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raul.plantshop.presentation.home.CategoryTabs
import com.raul.plantshop.presentation.home.Discount
import com.raul.plantshop.presentation.home.DiscountBanner
import com.raul.plantshop.presentation.home.HomeHeader
import com.raul.plantshop.ui.theme.PlantShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlantShopTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        HomeHeader(){}
                        DiscountBanner(

                            discount = Discount(
                                imagePainter = painterResource(R.drawable.plant7),
                                rangeDates = "01 - 21 July",
                                percetageDiscount = 30
                            )
                        )
                        CategoryTabs {
                            println(it.toString())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlantShopTheme {
        Greeting(R.string.fav_plants.toString())
    }
}