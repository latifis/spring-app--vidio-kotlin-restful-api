package com.latif.vidio.repository

import com.latif.vidio.domain.entity.FavoriteEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FavoriteRepository : JpaRepository<FavoriteEntity, Long> {

//    fun findByUserId(userId: Long): List<FavoriteEntity>

}