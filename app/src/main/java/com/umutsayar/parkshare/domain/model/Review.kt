package com.umutsayar.parkshare.domain.model

data class Review(
    val id: String,
    val reviewerName: String,
    val reviewerImage: String?,
    val reviewerRating: Double,
    val rating: Int,
    val comment: String,
    val createdAt: String,
    val spotTitle: String?
)