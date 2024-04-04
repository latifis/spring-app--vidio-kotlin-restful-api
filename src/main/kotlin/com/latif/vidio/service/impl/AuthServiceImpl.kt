package com.latif.vidio.service.impl

import com.latif.vidio.domain.dto.req.ReqLoginDto
import com.latif.vidio.domain.dto.req.ReqLoginJwtDto
import com.latif.vidio.domain.dto.res.ResLoginDto
import com.latif.vidio.exception.DataNotFoundException
import com.latif.vidio.repository.UserRepository
import com.latif.vidio.service.AuthService
import com.latif.vidio.util.JWTGenerator
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl (
    val userRepository: UserRepository
): AuthService{
    override fun login(req: ReqLoginDto): ResLoginDto {

        val token: String
        val id: Long

        val passwordEncoder = BCryptPasswordEncoder()

        val userExist = userRepository.findByUserName(req.userName)
        if (userExist == null){
            throw DataNotFoundException("Username does not exist")
        }else{
            if(!passwordEncoder.matches(req.password, userExist.password)){
                throw IllegalArgumentException("Invalid password")
            }
            val userData = ReqLoginJwtDto(
                idUser = userExist.idUser!!,
                userName = userExist.userName!!,
                email = userExist.email!!,
                idType = userExist.idType?.idType!!
            )
            id = userExist.idUser
            token = JWTGenerator().login(userData)
        }
        return ResLoginDto(id = id, token = token)
    }
}