package com.raul.plantshop.domain.review

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val user: String,
    val comment: String,
    val rating: Double
)


