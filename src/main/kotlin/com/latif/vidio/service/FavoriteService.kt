package com.latif.vidio.service

import com.latif.vidio.domain.dto.req.ReqFavoriteDto
import com.latif.vidio.domain.dto.res.ResMessageDto
import com.latif.vidio.domain.dto.res.ResFavoriteDto

interface FavoriteService {

    fun insert(req: ReqFavoriteDto): ResMessageDto<ResFavoriteDto>

    fun update(id: Long, req: ReqFavoriteDto): ResMessageDto<ResFavoriteDto>

    fun detail(id: Long): ResMessageDto<ResFavoriteDto>

    fun list(): ResMessageDto<List<ResFavoriteDto>>

    fun delete(id: Long): ResMessageDto<String>

}