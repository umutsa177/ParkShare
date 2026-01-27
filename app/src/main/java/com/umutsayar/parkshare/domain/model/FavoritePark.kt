package com.umutsayar.parkshare.domain.model

data class FavoritePark(
    val id: String,
    val title: String,
    val price: String,
    val rating: Double,
    val distance: String,
    val imageUrl: String
)