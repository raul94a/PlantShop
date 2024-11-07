package com.raul.plantshop.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.raul.plantshop.Screens
import com.raul.plantshop.presentation.plants.PlantState
import com.raul.plantshop.presentation.plants.PlantsViewModel
import com.raul.plantshop.ui.theme.Typography
import com.raul.plantshop.ui.theme.buttonColor

//initializing the data class with default parameters
private data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""
) {

    //function to get the list of bottomNavigationItems
    fun bottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Home",
                icon = Icons.Outlined.Home,
                route = Screens.Home.route
            ),
            BottomNavigationItem(
                label = "Favs",
                icon = Icons.Outlined.FavoriteBorder,
                route = Screens.Favs.route
            ),
            BottomNavigationItem(
                label = "Cart",
                icon = Icons.Outlined.ShoppingCart,
                route = Screens.Cart.route
            ),
            BottomNavigationItem(
                label = "Profile",
                icon = Icons.Outlined.Person,
                route = Screens.Profile.route
            ),
        )
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    selectedIndex: Int,
    viewModel: PlantsViewModel,
    onSelect: (Int) -> Unit
) {
    NavigationBar {


        BottomNavigationItem().bottomNavigationItems().forEachIndexed { index, navigationItem ->
            //Todo: la dependencia de índices no me gusta, mejor comp. por enum/object
            if (index != 2) NavigationBarItem(
                selected = index == selectedIndex,
//                label = {
//                    Text(navigationItem.label)
//                },
                icon = {
                    Icon(
                        navigationItem.icon,
                        contentDescription = navigationItem.label
                    )
                },
                onClick = {
                    onSelect(index)
                }
            )
            else {
                val state = viewModel.homeStateFlow.collectAsState(PlantState()).value
                val totalItems = state.shoppingCart.values().size
                NavigationBarItem(
                    selected = index == selectedIndex,
                    icon = {

                        BadgedBox(
                            badge = {
                                if (totalItems > 0) {
                                    Badge(
                                        containerColor = buttonColor
                                    ) {
                                        Text(
                                            "$totalItems",
                                            textAlign = TextAlign.Center,
                                            style = Typography.bodySmall.copy(color = Color.White),

                                            )
                                    }
                                }
                            }

                        ) {

                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.label
                            )


                        }

                    },
                    onClick = {
                        onSelect(index)
                    }
                )
            }


        }
    }
}