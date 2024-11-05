package com.raul.plantshop.domain.plants



sealed class PlantCategory {


    data object All : PlantCategory() {}
    data object Indoor : PlantCategory() {}
    data object Outdoor : PlantCategory() {}
    data object Popular : PlantCategory() {}

}