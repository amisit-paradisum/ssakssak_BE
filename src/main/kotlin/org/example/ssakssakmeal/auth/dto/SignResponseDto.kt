package org.example.ssakssakmeal.auth.dto

data class SignResponseDto (
    val jwt:String,
    val refreshToken: String,
)