package com.latif.vidio.service

import com.latif.vidio.domain.dto.req.ReqLoginDto
import com.latif.vidio.domain.dto.res.ResLoginDto

interface AuthService {

    fun login(req: ReqLoginDto): ResLoginDto

}