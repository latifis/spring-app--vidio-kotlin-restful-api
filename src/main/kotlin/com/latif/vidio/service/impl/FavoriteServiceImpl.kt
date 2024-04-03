package com.latif.vidio.service.impl

import com.latif.vidio.domain.dto.req.ReqFavoriteDto
import com.latif.vidio.domain.dto.res.ResFavoriteDto
import com.latif.vidio.domain.dto.res.ResMessageDto
import com.latif.vidio.domain.dto.res.ResVidioDto
import com.latif.vidio.domain.entity.*
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
        TODO("Not yet implemented")
    }

    override fun detail(id: Long): ResMessageDto<ResFavoriteDto> {
        TODO("Not yet implemented")
    }

    override fun list(): ResMessageDto<List<ResFavoriteDto>> {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long): ResMessageDto<String> {
        TODO("Not yet implemented")
    }
}