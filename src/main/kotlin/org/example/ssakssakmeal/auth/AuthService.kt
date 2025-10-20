package org.example.ssakssakmeal.auth

import org.example.ssakssakmeal.Entities.UserInfoEntity
import org.example.ssakssakmeal.auth.dto.SignResponseDto
import org.example.ssakssakmeal.auth.entity.RefreshTokenEntity
import org.example.ssakssakmeal.auth.oauth2.JwtUtil
import org.example.ssakssakmeal.auth.repository.RefreshTokenRepository
import org.example.ssakssakmeal.repository.UserInfoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    var userInfoRepository: UserInfoRepository,
    var refreshTokenRepository: RefreshTokenRepository,
    var jwtUtil: JwtUtil,
) {
    @Transactional
    fun checkSignUpAvailable(id: String){
        userInfoRepository.findById(id)
    }
    @Transactional
    fun signUp(id: String) : SignResponseDto? {
        if(!userInfoRepository.findById(id).isEmpty) return null
        userInfoRepository.save(UserInfoEntity(id))

        return signIn(id)
    }
    @Transactional
    fun signIn(id: String): SignResponseDto? {
        val search = userInfoRepository.findById(id)
        if(search.isEmpty) return null
        val token = jwtUtil.createToken(id)
        val refresh = jwtUtil.createRefreshToken(id)
        return SignResponseDto(token, refresh)
    }
    @Transactional
    fun signInWithRefresh(refreshToken: String): SignResponseDto? {
        val search = refreshTokenRepository.findById(refreshToken)
            .orElse(null)
        if(search == null) return null
        if(!jwtUtil.validateToken(search.refreshToken)) return null
        val userId = jwtUtil.getUserIdFromToken(search.refreshToken)
        if(userId.isNullOrBlank()) return null
        val newToken = jwtUtil.createToken(userId)
        val newRefresh = jwtUtil.createRefreshToken(userId)
        refreshTokenRepository.save(RefreshTokenEntity(newRefresh,jwtUtil.createRefreshExpireDate()))
        return SignResponseDto(newToken, newRefresh)
    }
    @Transactional
    fun updateUserGrade(id: String, newGrade: Int) {
        val user = userInfoRepository.findById(id)
            .orElseThrow { IllegalArgumentException("User not found") }

        user.grade = newGrade
        // 영속성 컨텍스트가 감지해서 commit 시 자동 UPDATE
    }
    @Transactional
    fun updateUserClassValue(id: String, newClassValue: Int) {
        val user = userInfoRepository.findById(id)
        .orElseThrow { IllegalArgumentException("User not found") }
        user.class_value = newClassValue
    }

}