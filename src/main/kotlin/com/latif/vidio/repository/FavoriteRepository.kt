package com.latif.vidio.repository

import com.latif.vidio.domain.entity.FavoriteEntity
import com.latif.vidio.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FavoriteRepository : JpaRepository<FavoriteEntity, Long> {

    fun findByIdUser(userId: UserEntity?): List<FavoriteEntity>
}