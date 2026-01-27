package com.umutsayar.parkshare.data.remote.api.review

import com.umutsayar.parkshare.data.remote.dto.*
import com.umutsayar.parkshare.data.remote.dto.review.*
import retrofit2.Response
import retrofit2.http.*

interface ReviewService {
    @POST("reviews")
    suspend fun createReview(
        @Body request: CreateReviewRequest
    ): Response<ApiResponse<Unit>>

    @GET("reviews/user/{userId}")
    suspend fun getUserReviews(
        @Path("userId") userId: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): Response<ApiResponse<ReviewsResponseDto>>

    @GET("reviews/spot/{spotId}")
    suspend fun getSpotReviews(
        @Path("spotId") spotId: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): Response<ApiResponse<ReviewsResponseDto>>

    @GET("reviews/my-reviews")
    suspend fun getMyReviews(): Response<ApiResponse<ReviewsResponseDto>>
}