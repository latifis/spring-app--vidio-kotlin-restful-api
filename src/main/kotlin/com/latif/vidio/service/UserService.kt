package com.latif.vidio.service

import com.latif.vidio.domain.dto.req.ReqUserDto
import com.latif.vidio.domain.dto.res.ResMessageDto
import com.latif.vidio.domain.dto.res.ResUserDto

interface UserService {

    fun insert(req: ReqUserDto): ResMessageDto<ResUserDto>

    fun update(id: Long, req: ReqUserDto): ResMessageDto<ResUserDto>

    fun detail(id: Long): ResMessageDto<ResUserDto>

    fun list(): ResMessageDto<List<ResUserDto>>

    fun delete(id: Long): ResMessageDto<String>

}