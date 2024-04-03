package com.latif.vidio.service.impl

import com.latif.vidio.domain.dto.req.ReqVidioDto
import com.latif.vidio.domain.dto.res.ResMessageDto
import com.latif.vidio.domain.dto.res.ResUserDto
import com.latif.vidio.domain.dto.res.ResVidioDto
import com.latif.vidio.domain.entity.GenreEntity
import com.latif.vidio.domain.entity.TypeUserEntity
import com.latif.vidio.domain.entity.VidioEntity
import com.latif.vidio.exception.DataExist
import com.latif.vidio.repository.GenreRepository
import com.latif.vidio.repository.TypeUserRepository
import com.latif.vidio.repository.VidioRepository
import com.latif.vidio.service.VidioService
import org.springframework.stereotype.Service
import java.util.*

@Service
class VidioServiceImpl (
    val vidioRepository: VidioRepository,
    val typeUserRepository: TypeUserRepository,
    val genreRepository: GenreRepository
) : VidioService {
    override fun insert(req: ReqVidioDto): ResMessageDto<ResVidioDto> {
        val existingNameVidio = vidioRepository.findByNameVidio(req.nameVidio)

        if (existingNameVidio != null) {
            throw DataExist("Nama Vidio Sudah Ada")
        } else {

            var idType: TypeUserEntity? = TypeUserEntity(idType = "T0001")
            var idGenre: GenreEntity? = null

            if (req.typeId != null) {
                idType = typeUserRepository.findById(req.typeId).orElse(TypeUserEntity(idType = "T0001"))
            }

            if (req.idGenre != null) {
                idGenre = genreRepository.findById(req.idGenre).orElse(GenreEntity(idGenre = 1))
            }

            val insert = VidioEntity(
                nameVidio = req.nameVidio,
                creatorVidio = req.creatorVidio,
                typeId = idType,
                idGenre = idGenre,
                dtAdded = Date(),
                dtUpdated = Date()
            )

            val savedVidio = vidioRepository.save(insert)

            val resGenreDto = ResVidioDto(
                nameVidio = savedVidio.nameVidio,
                creatorVidio = savedVidio.creatorVidio,
                typeId = savedVidio.typeId?.idType.toString(),
                idGenre = savedVidio.idGenre?.idGenre,
                dtAdded = savedVidio.dtAdded,
                dtUpdated = savedVidio.dtUpdated
            )
            return ResMessageDto(data = resGenreDto)
        }
    }

    override fun update(id: Long, req: ReqVidioDto): ResMessageDto<ResVidioDto> {
        TODO("Not yet implemented")
    }

    override fun detail(id: Long): ResMessageDto<ResVidioDto> {
        TODO("Not yet implemented")
    }

    override fun list(): ResMessageDto<List<ResVidioDto>> {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long): ResMessageDto<String> {
        TODO("Not yet implemented")
    }
}