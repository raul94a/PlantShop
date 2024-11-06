package com.raul.plantshop.domain.plants

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Serializable
@Parcelize
sealed class PlantCategory : Parcelable {


    data object All : PlantCategory() {}
    data object Indoor : PlantCategory() {}
    data object Outdoor : PlantCategory() {}
    data object Popular : PlantCategory() {}

}