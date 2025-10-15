package auth

import com.nimbusds.openid.connect.sdk.claims.UserInfo
import org.example.ssakssakmeal.Entities.UserInfoEntity
import org.example.ssakssakmeal.repository.UserInfoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    var userInfoRepository: UserInfoRepository
) {
    @Transactional
    fun signUp(id: String) {
        userInfoRepository.save(UserInfoEntity(id))
    }
    @Transactional
    fun signIn(id: String) {

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