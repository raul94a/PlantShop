package com.raul.plantshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raul.plantshop.data.plant.PlantRepositoryImpl
import com.raul.plantshop.data.plant.PlantsApiImpl
import com.raul.plantshop.presentation.home.HomeScreen
import com.raul.plantshop.presentation.home.HomeViewModel
import com.raul.plantshop.ui.theme.PlantShopTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = PlantsApiImpl()
        val repo = PlantRepositoryImpl(api);



        enableEdgeToEdge()
        setContent {
            val viewModel = HomeViewModel(repo)
            PlantShopTheme {
                HomeScreen(viewModel = viewModel)

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