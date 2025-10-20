package org.example.ssakssakmeal.review.dto

data class ReviewCreateRequest(
    val id: String,
    val star: Int?,
    val school: String,
    val menu: String
)