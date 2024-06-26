package com.latif.vidio.domain.dto.res

import com.fasterxml.jackson.annotation.JsonInclude

data class ResMessageDto<T>(

    val status: String="200",

    val message: String="Success",

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var data: T?= null

)
