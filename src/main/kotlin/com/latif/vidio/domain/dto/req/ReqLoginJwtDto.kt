package com.latif.vidio.domain.dto.req

data class ReqLoginJwtDto(

    val idUser: Long,

    val userName: String,

    val email: String,

    val idType: String
)
