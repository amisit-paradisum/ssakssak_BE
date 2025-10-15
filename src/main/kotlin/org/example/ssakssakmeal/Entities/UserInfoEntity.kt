package org.example.ssakssakmeal.Entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user_info")
class UserInfoEntity(
    @Id
    @Column(length = 100, unique = true, nullable = false)
    val id: String,

){
    @Column(length = 100)
    var email: String? = null
    @Column(length = 100)
    var school: String? = null
    @Column
    var grade: Int? = null
    @Column(name = "class")
    var affiliationClass: Int? = null
}