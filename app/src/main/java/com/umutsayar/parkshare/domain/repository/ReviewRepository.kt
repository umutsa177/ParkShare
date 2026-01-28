package com.umutsayar.parkshare.domain.repository

import com.umutsayar.parkshare.domain.model.Resource
import com.umutsayar.parkshare.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    suspend fun createReview(
        reservationId: String,
        rating: Int,
        comment: String
    ): Flow<Resource<Unit>>

    suspend fun getUserReviews(
        userId: String,
        page: Int = 1,
        limit: Int = 10
    ): Flow<Resource<List<Review>>>

    suspend fun getSpotReviews(
        spotId: String,
        page: Int = 1,
        limit: Int = 10
    ): Flow<Resource<List<Review>>>

    suspend fun getMyReviews(): Flow<Resource<List<Review>>>
}