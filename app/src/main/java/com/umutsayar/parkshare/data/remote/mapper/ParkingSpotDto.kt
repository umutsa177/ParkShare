package com.umutsayar.parkshare.data.remote.mapper

import com.umutsayar.parkshare.data.remote.dto.parking.*
import com.umutsayar.parkshare.domain.model.*

fun ParkingSpotDto.toDomain(): ParkingSpot {
    return ParkingSpot(
        id = id,
        ownerId = ownerId?.id ?: "",
        ownerName = ownerId?.name ?: "Unknown",
        ownerRating = ownerId?.rating ?: 0.0,
        ownerPhone = ownerId?.phone,
        title = title,
        description = description,
        latitude = location.coordinates.getOrNull(1) ?: 0.0,
        longitude = location.coordinates.getOrNull(0) ?: 0.0,
        fullAddress = address.fullAddress,
        district = address.district,
        city = address.city,
        hourlyPrice = pricing.hourly,
        dailyPrice = pricing.daily,
        monthlyPrice = pricing.monthly,
        features = ParkingFeature.fromApiList(features),
        photos = photos,
        isAvailable = availability,
        rating = rating,
        reviewCount = reviewCount,
        totalReservations = totalReservations
    )
}

fun List<ParkingSpotDto>.toDomainList(): List<ParkingSpot> {
    return map { it.toDomain() }
}

// Domain to DTO
fun ParkingSpot.toCreateRequest(): CreateParkingSpotRequest {
    return CreateParkingSpotRequest(
        title = title,
        description = description,
        location = LocationDto(
            type = "Point",
            coordinates = listOf(longitude, latitude)
        ),
        address = AddressDto(
            street = null,
            district = district,
            city = city,
            postalCode = null,
            fullAddress = fullAddress
        ),
        pricing = PricingDto(
            hourly = hourlyPrice,
            daily = dailyPrice,
            monthly = monthlyPrice
        ),
        features = features.map { it.apiValue },
        photos = photos
    )
}