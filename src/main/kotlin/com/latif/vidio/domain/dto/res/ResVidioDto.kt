package com.latif.vidio.domain.dto.res

import java.util.*

data class ResVidioDto(

    val nameVidio: String? = null,

    val creatorVidio: String? = null,

    val typeId: String? = null,

    var idGenre: Long? = null,

    var dtAdded: Date? = null,

    var dtUpdated: Date? = null

)
