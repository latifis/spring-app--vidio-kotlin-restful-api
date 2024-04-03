package com.latif.vidio.repository

import com.latif.vidio.domain.entity.GenreEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GenreRepository : JpaRepository<GenreEntity, Long> {

    fun findByGenreName(genreName: String?): GenreEntity?

}