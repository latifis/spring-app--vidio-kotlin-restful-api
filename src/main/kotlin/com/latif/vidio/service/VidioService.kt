package com.latif.vidio.service

import com.latif.vidio.domain.dto.req.ReqVidioDto
import com.latif.vidio.domain.dto.res.ResMessageDto
import com.latif.vidio.domain.dto.res.ResVidioDto

interface VidioService {

    fun insert(type: String?, req: ReqVidioDto): ResMessageDto<ResVidioDto>

    fun update(id: Long, req: ReqVidioDto): ResMessageDto<ResVidioDto>

    fun detail(id: Long): ResMessageDto<ResVidioDto>

    fun list(): ResMessageDto<List<ResVidioDto>>

    fun delete(id: Long): ResMessageDto<String>

}