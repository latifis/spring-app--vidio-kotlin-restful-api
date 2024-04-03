package com.latif.vidio.domain.dto.req

import com.latif.vidio.domain.entity.UserEntity
import com.latif.vidio.domain.entity.VidioEntity
import jakarta.persistence.Column
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.util.*

data class ReqFavoriteDto(

    var idVidio: Long? = null,

    var idUser: Long? = null,

    var dtAdded: Date? = null,

    var dtUpdated: Date? = null

)
