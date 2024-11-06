package com.raul.plantshop.domain.review

import kotlinx.serialization.Serializable

@Serializable
class Rating(val reviews: MutableList<Review>) {



    fun getRatingMean(): Double {

        val length = reviews.size
        val sum = reviews.fold(0.0){ acc, review ->

            return acc + review.rating
        }

        return sum / length

    }


}