package com.latif.vidio.repository

import com.latif.vidio.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {

    fun findByUserName(userName: String?): UserEntity?

    fun findByEmail(email: String?): UserEntity?

}