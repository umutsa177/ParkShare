package com.umutsayar.parkshare.data.remote.mapper

import com.umutsayar.parkshare.data.remote.dto.review.ReviewDto
import com.umutsayar.parkshare.domain.model.Review

fun ReviewDto.toDomain(): Review {
    return Review(
        id = id,
        reviewerName = userId?.name ?: "Anonymous",
        reviewerImage = userId?.profileImage,
        reviewerRating = userId?.rating ?: 0.0,
        rating = rating,
        comment = comment,
        createdAt = createdAt,
        spotTitle = null // Will be populated from reservation if needed
    )
}

fun List<ReviewDto>.toDomainList(): List<Review> {
    return map { it.toDomain() }
}