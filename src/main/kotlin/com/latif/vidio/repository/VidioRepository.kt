package com.latif.vidio.repository

import com.latif.vidio.domain.entity.TypeUserEntity
import com.latif.vidio.domain.entity.VidioEntity
import org.springframework.data.jpa.repository.JpaRepository

interface VidioRepository : JpaRepository<VidioEntity, Long> {

    fun findByNameVidio(nameVidio: String?): VidioEntity?

    fun findByTypeId(typeId: TypeUserEntity?): List<VidioEntity>

    fun findByTypeIdAndNameVidioContainingIgnoreCase(typeId: TypeUserEntity, name: String): List<VidioEntity>

    fun findByTypeIdAndCreatorVidioContainingIgnoreCase(typeId: TypeUserEntity, creator: String): List<VidioEntity>

    fun findByNameVidioContainingIgnoreCase(name: String): List<VidioEntity>

    fun findByCreatorVidioContainingIgnoreCase(creator: String): List<VidioEntity>

}