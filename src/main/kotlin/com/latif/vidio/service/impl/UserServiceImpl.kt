package com.latif.vidio.service.impl

import com.latif.vidio.domain.dto.req.ReqUserDto
import com.latif.vidio.domain.dto.res.ResMessageDto
import com.latif.vidio.domain.dto.res.ResUserDto
import com.latif.vidio.domain.entity.TypeUserEntity
import com.latif.vidio.domain.entity.UserEntity
import com.latif.vidio.exception.DataExist
import com.latif.vidio.repository.TypeUserRepository
import com.latif.vidio.repository.UserRepository
import com.latif.vidio.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
    val userRepository: UserRepository,
    val typeUserRepository: TypeUserRepository
) : UserService {
    override fun insert(req: ReqUserDto): ResMessageDto<ResUserDto> {
        val existingUsername = userRepository.findByUserName(req.userName)
        val existingEmail = userRepository.findByEmail(req.email)

        if (existingEmail != null && existingUsername != null) {
            throw DataExist("Username dan Email Profil Sudah Ada")
        } else if (existingEmail != null) {
            throw DataExist("Email Profil Sudah Ada")
        } else if (existingUsername != null) {
            throw DataExist("Username Profil Sudah Ada")
        } else {

            var idType: TypeUserEntity? = null

            if (req.idType != null){
                idType = typeUserRepository.findById(req.idType).orElse(null)
            }

            val insert = UserEntity(
                userName = req.userName,
                email = req.email,
                password = req.password,
                idType = idType
            )

            val savedUser = userRepository.save(insert)

            val resProfileDto = ResUserDto(
                userName = savedUser.userName,
                email = savedUser.email,
                idType = savedUser.idType.toString()
            )
            return ResMessageDto(data = resProfileDto)
        }
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