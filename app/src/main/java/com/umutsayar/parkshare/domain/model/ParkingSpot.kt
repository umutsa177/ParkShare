package com.umutsayar.parkshare.domain.model

data class ParkingSpot(
    val id: String,
    val ownerId: String,
    val ownerName: String,
    val ownerRating: Double,
    val ownerPhone: String?,
    val title: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val fullAddress: String,
    val district: String?,
    val city: String?,
    val hourlyPrice: Double,
    val dailyPrice: Double,
    val monthlyPrice: Double,
    val features: List<ParkingFeature>,
    val photos: List<String>,
    val isAvailable: Boolean,
    val rating: Double,
    val reviewCount: Int,
    val totalReservations: Int,
    val distance: Double? = null, // km cinsinden
    val isFavorite: Boolean = false
)

enum class ParkingFeature(val displayName: String, val apiValue: String) {
    COVERED("Kapalı", "covered"),
    SECURED("Güvenli", "secured"),
    ELECTRIC("Elektrikli Şarj", "electric"),
    CCTV("Kameralı", "cctv"),
    ACCESSIBLE("Engelli Erişimi", "accessible"),
    VALET("Vale Hizmeti", "valet");

    companion object {
        fun fromApiValue(value: String): ParkingFeature? {
            return ParkingFeature.entries.find { it.apiValue == value }
        }

        fun fromApiList(apiList: List<String>): List<ParkingFeature> {
            return apiList.mapNotNull { fromApiValue(it) }
        }
    }
}