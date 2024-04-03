package com.latif.vidio.service

import com.latif.vidio.domain.dto.req.ReqGenreDto
import com.latif.vidio.domain.dto.res.ResMessageDto
import com.latif.vidio.domain.dto.res.ResGenreDto

interface GenreService {

    fun insert(req: ReqGenreDto): ResMessageDto<ResGenreDto>

    fun update(id: Long, req: ReqGenreDto): ResMessageDto<ResGenreDto>

    fun detail(id: Long): ResMessageDto<ResGenreDto>

    fun list(): ResMessageDto<List<ResGenreDto>>

    fun delete(id: Long): ResMessageDto<String>

}