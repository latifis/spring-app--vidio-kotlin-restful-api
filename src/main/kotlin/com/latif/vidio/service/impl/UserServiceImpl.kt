package com.latif.vidio.service.impl

import com.latif.vidio.domain.dto.req.ReqUserDto
import com.latif.vidio.domain.dto.res.ResMessageDto
import com.latif.vidio.domain.dto.res.ResUserDto
import com.latif.vidio.domain.entity.TypeUserEntity
import com.latif.vidio.domain.entity.UserEntity
import com.latif.vidio.exception.DataExist
import com.latif.vidio.exception.DataNotFoundException
import com.latif.vidio.repository.TypeUserRepository
import com.latif.vidio.repository.UserRepository
import com.latif.vidio.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
    val userRepository: UserRepository,
    val typeUserRepository: TypeUserRepository
) : UserService {
    override fun insert(type: String?,req: ReqUserDto): ResMessageDto<ResUserDto> {
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

            if (type != null){
                idType = typeUserRepository.findById(type).orElse(TypeUserEntity(idType = "T0001"))
            } else {
                idType = TypeUserEntity(idType = "T0001")
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
                idType = savedUser.idType?.idType.toString()
            )
            return ResMessageDto(data = resProfileDto)
        }
    }

    override fun update(id: Long, req: ReqUserDto): ResMessageDto<ResUserDto> {
        val checkId = userRepository.findById(id)

        if(!checkId.isPresent)
            throw DataNotFoundException("ID Profile Tidak Ada")

        if (req.idType != null){
            checkId.get().idType = typeUserRepository.findById(req.idType).orElse(null)
        }

        checkId.get().userName = req.userName
        checkId.get().email = req.email
        checkId.get().password = req.password

        val updateProfile = userRepository.save(checkId.get())
        val resProfileDto = ResUserDto(
            userName = updateProfile.userName,
            email = updateProfile.email,
            idType = updateProfile.idType?.idType.toString()
        )
        return ResMessageDto(data = resProfileDto)
    }

    override fun detail(id: Long): ResMessageDto<ResUserDto> {
        val checkId = userRepository.findById(id)

        if(!checkId.isPresent)
            throw DataNotFoundException("ID Profile Tidak Ada")

        val response = ResUserDto(
            userName = checkId.get().userName,
            email = checkId.get().email,
            idType = checkId.get().idType?.idType.toString()
        )

        return ResMessageDto(data = response)
    }

    override fun list(): ResMessageDto<List<ResUserDto>> {
        val userList = userRepository.findAll()

        val responseList = arrayListOf<ResUserDto>()
        for (user in userList){
            val data = ResUserDto(
                userName = user.userName,
                email = user.email,
                idType = user.idType?.idType.toString()
            )
            responseList.add(data)
        }

        return ResMessageDto(data = responseList)
    }

    override fun delete(id: Long): ResMessageDto<String> {
        val checkId = userRepository.findById(id)

        if(!checkId.isPresent)
            throw DataNotFoundException("ID Profile Tidak Ada")

        userRepository.deleteById(id)

        return ResMessageDto()
    }

}