package org.example.ssakssakmeal.auth.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.Instant
import java.util.Date

@Entity
class RefreshTokenEntity (
    @Id
    @Column(unique = true, nullable = false, name = "token")
    val refreshToken: String = "",

    @Column(nullable = false, name = "expire_time")
    val expiredAt: Date = Date.from(Instant.now()),
)