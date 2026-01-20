package com.umutsayar.parkshare.presentation.bookings.model

data class BookingModel(
    val title: String,
    val address: String,
    val dateTime: String,
    val price: String,
    val status: BookingStatus,
    val imageRes: Int
)


