package com.latif.vidio.domain.dto.req

import jakarta.validation.constraints.NotBlank
import java.util.*

data class ReqVidioDto(

    @field:NotBlank(message = "Nama Vidio tidak boleh kosong")
    val nameVidio: String? = null,

    @field:NotBlank(message = "Creator Vidio tidak boleh kosong")
    val creatorVidio: String? = null,

    val typeId: String? = null,

    @field:NotBlank(message = "Id Genre tidak boleh kosong")
    var idGenre: Long,

    var dtAdded: Date? = null,

    var dtUpdated: Date? = null

)

