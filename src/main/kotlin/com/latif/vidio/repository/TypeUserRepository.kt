package com.latif.vidio.repository

import com.latif.vidio.domain.entity.TypeUserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TypeUserRepository : JpaRepository<TypeUserEntity, String> {
}