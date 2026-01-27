package com.umutsayar.parkshare.domain.model

data class Reservation(
    val id: String,
    val spotId: String,
    val spotTitle: String,
    val spotAddress: String,
    val spotPhotos: List<String>,
    val renterId: String,
    val renterName: String,
    val renterRating: Double,
    val renterPhone: String?,
    val ownerId: String,
    val ownerName: String,
    val ownerPhone: String?,
    val startDate: String,
    val endDate: String,
    val totalPrice: Double,
    val pricingType: PricingType,
    val durationHours: Int,
    val durationDays: Int,
    val status: ReservationStatus,
    val cancellationReason: String?,
    val notes: String?,
    val createdAt: String
)

enum class PricingType(val displayName: String, val apiValue: String) {
    HOURLY("Saatlik", "hourly"),
    DAILY("Günlük", "daily"),
    MONTHLY("Aylık", "monthly");

    companion object {
        fun fromApiValue(value: String): PricingType {
            return PricingType.entries.find { it.apiValue == value } ?: HOURLY
        }
    }
}

enum class ReservationStatus(val displayName: String, val apiValue: String) {
    PENDING("Bekliyor", "pending"),
    CONFIRMED("Onaylandı", "confirmed"),
    ACTIVE("Aktif", "active"),
    COMPLETED("Tamamlandı", "completed"),
    CANCELLED("İptal Edildi", "cancelled");

    companion object {
        fun fromApiValue(value: String): ReservationStatus {
            return ReservationStatus.entries.find { it.apiValue == value } ?: PENDING
        }
    }
}
