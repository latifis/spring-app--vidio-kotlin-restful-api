package com.latif.vidio.repository

import com.latif.vidio.domain.entity.VidioEntity
import org.springframework.data.jpa.repository.JpaRepository

interface VidioRepository : JpaRepository<VidioEntity, Long> {
}