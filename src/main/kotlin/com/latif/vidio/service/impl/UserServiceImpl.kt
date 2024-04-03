package com.latif.vidio.service.impl

import com.latif.vidio.domain.dto.req.ReqUserDto
import com.latif.vidio.domain.dto.res.ResMessageDto
import com.latif.vidio.domain.dto.res.ResUserDto
import com.latif.vidio.repository.TypeUserRepository
import com.latif.vidio.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
    val userRepository: TypeUserRepository
) : UserService {
    override fun insert(req: ReqUserDto): ResMessageDto<ResUserDto> {
        TODO("Not yet implemented")
    }

    override fun update(id: Long, req: ReqUserDto): ResMessageDto<ResUserDto> {
        TODO("Not yet implemented")
    }

    override fun detail(id: Long): ResMessageDto<ResUserDto> {
        TODO("Not yet implemented")
    }

    override fun list(): ResMessageDto<List<ResUserDto>> {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long): ResMessageDto<String> {
        TODO("Not yet implemented")
    }

}