package com.latif.vidio.domain.dto.req

import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull
import java.util.*

data class ReqVidioDto(

    @field:NotBlank(message = "Nama Vidio tidak boleh kosong")
    val nameVidio: String? = null,

    @field:NotBlank(message = "Creator Vidio tidak boleh kosong")
    val creatorVidio: String? = null,

    val typeId: String? = null,

//    @field:NotNull
    val idGenre: Long,

    val dtAdded: Date? = null,

    val dtUpdated: Date? = null

)

