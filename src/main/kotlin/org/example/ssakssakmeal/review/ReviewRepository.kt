package org.example.ssakssakmeal.review

import org.example.ssakssakmeal.Entities.Review
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : JpaRepository<Review, String> {
    fun findBySchool(school: String): List<Review>
    fun findByStar(star: Int): List<Review>
}