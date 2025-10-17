package org.example.ssakssakmeal.Entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.OffsetDateTime

@Entity
@Table(name = "reviews")
data class Review(
    @Id
    @Column(length = 100, nullable = false)
    val id: String,

    @Column(nullable = true)
    val star: Int? = null,

    @Column(length = 100, nullable = false)
    val school: String,

    @Column(nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    val date: OffsetDateTime,

    @Column(nullable = false, columnDefinition = "TEXT")
    val menu: String
)