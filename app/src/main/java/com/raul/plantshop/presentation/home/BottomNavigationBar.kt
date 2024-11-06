package com.raul.plantshop.presentation.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.raul.plantshop.Screens

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
                label = "Profile",
                icon = Icons.Outlined.Person,
                route = Screens.Profile.route
            ),
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, selectedIndex: Int, onSelect: (Int) -> Unit) {
    NavigationBar {





        BottomNavigationItem().bottomNavigationItems().forEachIndexed { index, navigationItem ->

            NavigationBarItem(
                selected = index == selectedIndex,
                label = {
                    Text(navigationItem.label)
                },
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



        }
    }
}