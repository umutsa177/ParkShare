package com.umutsayar.parkshare.data.remote.mapper

import com.umutsayar.parkshare.data.remote.dto.reservation.*
import com.umutsayar.parkshare.domain.model.*

fun ReservationDto.toDomain(): Reservation {
    return Reservation(
        id = id,
        spotId = spotId?.id ?: "",
        spotTitle = spotId?.title ?: "Unknown",
        spotAddress = spotId?.address?.fullAddress ?: "",
        spotPhotos = spotId?.photos ?: emptyList(),
        renterId = renterId?.id ?: "",
        renterName = renterId?.name ?: "Unknown",
        renterRating = renterId?.rating ?: 0.0,
        renterPhone = renterId?.phone,
        ownerId = ownerId?.id ?: "",
        ownerName = ownerId?.name ?: "Unknown",
        ownerPhone = ownerId?.phone,
        startDate = startDate,
        endDate = endDate,
        totalPrice = totalPrice,
        pricingType = PricingType.fromApiValue(pricingType),
        durationHours = duration?.hours ?: 0,
        durationDays = duration?.days ?: 0,
        status = ReservationStatus.fromApiValue(status),
        cancellationReason = cancellationReason,
        notes = notes,
        createdAt = createdAt
    )
}

fun List<ReservationDto>.toDomainList(): List<Reservation> {
    return map { it.toDomain() }
}