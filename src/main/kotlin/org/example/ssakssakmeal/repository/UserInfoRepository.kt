package org.example.ssakssakmeal.repository

import com.nimbusds.openid.connect.sdk.claims.UserInfo
import org.example.ssakssakmeal.Entities.UserInfoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserInfoRepository: JpaRepository<UserInfoEntity, String> {
    fun save(entity: UserInfoEntity): UserInfoEntity
    fun findByEmail(email: String): UserInfoEntity?
    fun changeGrade(grade: Int)


}