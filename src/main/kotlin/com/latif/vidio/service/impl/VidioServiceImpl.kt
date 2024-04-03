package com.latif.vidio.service.impl

import com.latif.vidio.config.AuthInterceptor
import com.latif.vidio.domain.dto.req.ReqVidioDto
import com.latif.vidio.domain.dto.res.ResMessageDto
import com.latif.vidio.domain.dto.res.ResVidioDto
import com.latif.vidio.domain.entity.GenreEntity
import com.latif.vidio.domain.entity.TypeUserEntity
import com.latif.vidio.domain.entity.VidioEntity
import com.latif.vidio.exception.DataExist
import com.latif.vidio.exception.DataNotFoundException
import com.latif.vidio.exception.InvalidToken
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
    override fun insert(type: String?, req: ReqVidioDto): ResMessageDto<ResVidioDto> {
        val existingNameVidio = vidioRepository.findByNameVidio(req.nameVidio)

        if (existingNameVidio != null) {
            throw DataExist("Nama Vidio Sudah Ada")
        } else {


            var idType: TypeUserEntity? = null
            var idGenre: GenreEntity? = null

            if (req.idGenre != null) {
                idGenre = genreRepository.findById(req.idGenre).orElse(GenreEntity(idGenre = 1))
            }

            if (type != null){
                idType = typeUserRepository.findById(type).orElse(TypeUserEntity(idType = "T0001"))
            } else {
                idType = TypeUserEntity(idType = "T0001")
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
        val checkId = vidioRepository.findById(id)

        if(!checkId.isPresent)
            throw DataNotFoundException("ID Vidio Tidak Ada")

        if (req.typeId != null){
            checkId.get().typeId = typeUserRepository.findById(req.typeId).orElse(checkId.get().typeId)
        }

        if (req.idGenre != null){
            checkId.get().idGenre = genreRepository.findById(req.idGenre).orElse(checkId.get().idGenre)
        }

        checkId.get().nameVidio = req.nameVidio
        checkId.get().creatorVidio = req.creatorVidio
        checkId.get().dtUpdated = Date()

        val updateVidio = vidioRepository.save(checkId.get())

        val resVidioDto = ResVidioDto(
            nameVidio = updateVidio.nameVidio,
            creatorVidio = updateVidio.creatorVidio,
            typeId = updateVidio.typeId?.idType.toString(),
            idGenre = updateVidio.idGenre?.idGenre,
            dtAdded = updateVidio.dtAdded,
            dtUpdated = updateVidio.dtUpdated
        )

        return ResMessageDto(data = resVidioDto)
    }

    override fun detail(id: Long): ResMessageDto<ResVidioDto> {
        val checkId = vidioRepository.findById(id)

        if(!checkId.isPresent)
            throw DataNotFoundException("ID Vidio Tidak Ada")

        val response = ResVidioDto(
            nameVidio = checkId.get().nameVidio,
            creatorVidio = checkId.get().creatorVidio,
            typeId = checkId.get().typeId?.idType.toString(),
            idGenre = checkId.get().idGenre?.idGenre,
            dtAdded = checkId.get().dtAdded,
            dtUpdated = checkId.get().dtUpdated
        )

        return ResMessageDto(data = response)
    }

    override fun list(): ResMessageDto<List<ResVidioDto>> {
        val typeId = AuthInterceptor.typeId
        val responseList = mutableListOf<ResVidioDto>()

        if (typeId == "T0001") {
            val vidioList = vidioRepository.findByTypeId(TypeUserEntity(idType = typeId))
            for (vidio in vidioList) {
                val data = ResVidioDto(
                    nameVidio = vidio.nameVidio,
                    creatorVidio = vidio.creatorVidio,
                    typeId = vidio.typeId?.idType.toString(),
                    idGenre = vidio.idGenre?.idGenre,
                    dtAdded = vidio.dtAdded,
                    dtUpdated = vidio.dtUpdated
                )
                responseList.add(data)
            }
        } else if (typeId == "T0002") {
            val vidioList = vidioRepository.findAll()
            for (vidio in vidioList) {
                val data = ResVidioDto(
                    nameVidio = vidio.nameVidio,
                    creatorVidio = vidio.creatorVidio,
                    typeId = vidio.typeId?.idType.toString(),
                    idGenre = vidio.idGenre?.idGenre,
                    dtAdded = vidio.dtAdded,
                    dtUpdated = vidio.dtUpdated
                )
                responseList.add(data)
            }
        }

        return ResMessageDto(data = responseList)
    }

    override fun delete(id: Long): ResMessageDto<String> {
        val checkId = vidioRepository.findById(id)

        if(!checkId.isPresent)
            throw DataNotFoundException("ID Vidio Tidak Ada")

        vidioRepository.deleteById(id)

        return ResMessageDto()
    }
}