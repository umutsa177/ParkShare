package com.umutsayar.parkshare.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val role: UserRole,
    val rating: Double,
    val reviewCount: Int,
    val profileImage: String?
)

enum class UserRole {
    OWNER,
    RENTER;

    companion object {
        fun fromString(role: String): UserRole {
            return when (role) {
                "owner" -> OWNER
                "renter" -> RENTER
                else -> RENTER
            }
        }
    }

    fun toApiString(): String {
        return when (this) {
            OWNER -> "owner"
            RENTER -> "renter"
        }
    }
}