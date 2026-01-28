package com.umutsayar.parkshare.data.repository

import com.umutsayar.parkshare.data.remote.api.review.ReviewService
import com.umutsayar.parkshare.data.remote.dto.review.*
import com.umutsayar.parkshare.data.remote.mapper.*
import com.umutsayar.parkshare.domain.model.*
import com.umutsayar.parkshare.domain.repository.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val reviewService: ReviewService
) : ReviewRepository {

    override suspend fun createReview(
        reservationId: String,
        rating: Int,
        comment: String
    ): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val response = reviewService.createReview(
                CreateReviewRequest(reservationId, rating, comment)
            )

            if (response.isSuccessful && response.body()?.success == true) {
                emit(Resource.Success(Unit))
            } else {
                emit(Resource.Error(response.body()?.message ?: "Değerlendirme eklenemedi"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Hata: ${e.message}"))
        }
    }

    override suspend fun getUserReviews(
        userId: String,
        page: Int,
        limit: Int
    ): Flow<Resource<List<Review>>> = flow {
        emit(Resource.Loading())
        try {
            val response = reviewService.getUserReviews(userId, page, limit)

            if (response.isSuccessful && response.body()?.success == true) {
                val reviews = response.body()?.data?.reviews ?: emptyList()
                emit(Resource.Success(reviews.toDomainList()))
            } else {
                emit(Resource.Error(response.body()?.message ?: "Değerlendirmeler alınamadı"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Hata: ${e.message}"))
        }
    }

    override suspend fun getSpotReviews(
        spotId: String,
        page: Int,
        limit: Int
    ): Flow<Resource<List<Review>>> = flow {
        emit(Resource.Loading())
        try {
            val response = reviewService.getSpotReviews(spotId, page, limit)

            if (response.isSuccessful && response.body()?.success == true) {
                val reviews = response.body()?.data?.reviews ?: emptyList()
                emit(Resource.Success(reviews.toDomainList()))
            } else {
                emit(Resource.Error(response.body()?.message ?: "Değerlendirmeler alınamadı"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Hata: ${e.message}"))
        }
    }

    override suspend fun getMyReviews(): Flow<Resource<List<Review>>> = flow {
        emit(Resource.Loading())
        try {
            val response = reviewService.getMyReviews()

            if (response.isSuccessful && response.body()?.success == true) {
                val reviews = response.body()?.data?.reviews ?: emptyList()
                emit(Resource.Success(reviews.toDomainList()))
            } else {
                emit(Resource.Error(response.body()?.message ?: "Değerlendirmeler alınamadı"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Hata: ${e.message}"))
        }
    }
}