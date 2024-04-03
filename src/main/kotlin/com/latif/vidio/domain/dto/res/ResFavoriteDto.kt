package com.latif.vidio.domain.dto.res

import java.util.*

data class ResFavoriteDto(

    var idVidio: Long? = null,

    var idUser: Long? = null,

    var dtAdded: Date? = null,

    var dtUpdated: Date? = null

)
