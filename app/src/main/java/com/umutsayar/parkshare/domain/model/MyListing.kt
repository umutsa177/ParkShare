package com.umutsayar.parkshare.domain.model

enum class ListingStatus { ACTIVE, PASSIVE, DRAFT }

data class MyListing(
    val id: String,
    val title: String,
    val location: String,
    val price: String,
    val rating: Double,
    val reviewCount: Int,
    val imageUrl: String,
    val status: ListingStatus,
    val viewCount: Int,
    val reservationCount: Int
)