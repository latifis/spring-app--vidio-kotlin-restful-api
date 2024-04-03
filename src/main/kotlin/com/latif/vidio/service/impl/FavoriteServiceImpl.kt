package com.latif.vidio.service.impl

import com.latif.vidio.domain.dto.req.ReqFavoriteDto
import com.latif.vidio.domain.dto.res.ResFavoriteDto
import com.latif.vidio.domain.dto.res.ResMessageDto
import com.latif.vidio.domain.dto.res.ResVidioDto
import com.latif.vidio.domain.entity.*
import com.latif.vidio.exception.DataNotFoundException
import com.latif.vidio.repository.FavoriteRepository
import com.latif.vidio.repository.UserRepository
import com.latif.vidio.repository.VidioRepository
import com.latif.vidio.service.FavoriteService
import org.springframework.stereotype.Service
import java.util.*

@Service
class FavoriteServiceImpl (
    val favoriteRepository: FavoriteRepository,
    val userRepository: UserRepository,
    val vidioRepository: VidioRepository
): FavoriteService{

    override fun insert(req: ReqFavoriteDto): ResMessageDto<ResFavoriteDto> {

        var idUser: UserEntity? = UserEntity(idUser = 1)
        var idVidio: VidioEntity? = VidioEntity(idVidio = 1)

        if (req.idUser != null) {
            idUser = userRepository.findById(req.idUser!!).orElse(UserEntity(idUser = 1))
        }

        if (req.idVidio != null) {
            idVidio = vidioRepository.findById(req.idVidio!!).orElse(VidioEntity(idVidio = 1))
        }

        val insert = FavoriteEntity(
            idUser = idUser,
            idVidio = idVidio,
            dtAdded = Date(),
            dtUpdated = Date()
        )

        val savedFavorite = favoriteRepository.save(insert)

        val resFavoriteDto = ResFavoriteDto(
            idUser = savedFavorite.idUser?.idUser,
            idVidio = savedFavorite.idVidio?.idVidio,
            dtAdded = savedFavorite.dtAdded,
            dtUpdated = savedFavorite.dtUpdated
        )
        return ResMessageDto(data = resFavoriteDto)
    }

    override fun update(id: Long, req: ReqFavoriteDto): ResMessageDto<ResFavoriteDto> {
        val checkId = favoriteRepository.findById(id)

        if(!checkId.isPresent)
            throw DataNotFoundException("ID Favorite Tidak Ada")

        if (req.idUser != null) {
            checkId.get().idUser = userRepository.findById(req.idUser!!).orElse(checkId.get().idUser)
        }

        if (req.idVidio != null) {
            checkId.get().idVidio = vidioRepository.findById(req.idVidio!!).orElse(checkId.get().idVidio)
        }

        checkId.get().dtUpdated = Date()

        val updateFavorite = favoriteRepository.save(checkId.get())

        val resFavoriteDto = ResFavoriteDto(
            idUser = updateFavorite.idUser?.idUser,
            idVidio = updateFavorite.idVidio?.idVidio,
            dtAdded = updateFavorite.dtAdded,
            dtUpdated = updateFavorite.dtUpdated
        )

        return ResMessageDto(data = resFavoriteDto)
    }

    override fun detail(id: Long): ResMessageDto<ResFavoriteDto> {
        val checkId = favoriteRepository.findById(id)

        if(!checkId.isPresent)
            throw DataNotFoundException("ID Favorite Tidak Ada")

        val response = ResFavoriteDto(
            idUser = checkId.get().idUser?.idUser,
            idVidio = checkId.get().idVidio?.idVidio,
            dtAdded = checkId.get().dtAdded,
            dtUpdated = checkId.get().dtUpdated
        )

        return ResMessageDto(data = response)
    }

    override fun list(): ResMessageDto<List<ResFavoriteDto>> {
        val favoriteList = favoriteRepository.findAll()

        val responseList = arrayListOf<ResFavoriteDto>()
        for (favorite in favoriteList){
            val data = ResFavoriteDto(
                idUser = favorite.idUser?.idUser,
                idVidio = favorite.idVidio?.idVidio,
                dtAdded = favorite.dtAdded,
                dtUpdated = favorite.dtUpdated
            )
            responseList.add(data)
        }

        return ResMessageDto(data = responseList)
    }

    override fun delete(id: Long): ResMessageDto<String> {
        TODO("Not yet implemented")
//        val checkId = favoriteRepository.findById(id)
//
//        if(!checkId.isPresent)
//            throw DataNotFoundException("ID Favorite Tidak Ada")
//
//        vidioRepository.deleteById(id)
//
//        return ResMessageDto()
    }
}