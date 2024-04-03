package com.latif.vidio.repository

import com.latif.vidio.domain.entity.TypeUserEntity
import com.latif.vidio.domain.entity.VidioEntity
import org.springframework.data.jpa.repository.JpaRepository

interface VidioRepository : JpaRepository<VidioEntity, Long> {

    fun findByNameVidio(nameVidio: String?): VidioEntity?

    fun findByTypeId(typeId: TypeUserEntity?): List<VidioEntity>

//    fun findByTypeIdAndNameVidioContainingIgnoreCaseOrTypeIdAndCreatorVidioContainingIgnoreCase(
//        typeId1: TypeUserEntity,
//        nameVidio: String?,
//        typeId2: TypeUserEntity,
//        creatorVidio: String?
//    ): List<VidioEntity>

}