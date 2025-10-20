package org.example.ssakssakmeal.auth.repository

import org.example.ssakssakmeal.auth.entity.RefreshTokenEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository: JpaRepository<RefreshTokenEntity, String>