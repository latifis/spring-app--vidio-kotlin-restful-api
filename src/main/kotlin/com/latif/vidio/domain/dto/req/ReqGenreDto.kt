package com.latif.vidio.domain.dto.req

import jakarta.validation.constraints.NotBlank

data class ReqGenreDto(

    @field:NotBlank(message = "Genre name tidak boleh kosong")
    val genreName: String? = null

)
