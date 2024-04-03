package com.latif.vidio.service.impl

import com.latif.vidio.domain.dto.req.ReqGenreDto
import com.latif.vidio.domain.dto.res.ResGenreDto
import com.latif.vidio.domain.dto.res.ResMessageDto
import com.latif.vidio.domain.dto.res.ResUserDto
import com.latif.vidio.domain.entity.GenreEntity
import com.latif.vidio.domain.entity.TypeUserEntity
import com.latif.vidio.domain.entity.UserEntity
import com.latif.vidio.exception.DataExist
import com.latif.vidio.exception.DataNotFoundException
import com.latif.vidio.repository.GenreRepository
import com.latif.vidio.service.GenreService
import org.springframework.stereotype.Service

@Service
class GenreServiceImpl (
    val genreRepository: GenreRepository
): GenreService {
    override fun insert(req: ReqGenreDto): ResMessageDto<ResGenreDto> {
        val existingGenre = genreRepository.findByGenreName(req.genreName)

        if (existingGenre != null) {
            throw DataExist("Genre Sudah Ada")
        } else {

            val insert = GenreEntity(
                genreName = req.genreName
            )

            val savedGenre = genreRepository.save(insert)

            val resGenreDto = ResGenreDto(
                genreName = savedGenre.genreName
            )
            return ResMessageDto(data = resGenreDto)
        }
    }

    override fun update(id: Long, req: ReqGenreDto): ResMessageDto<ResGenreDto> {
        val checkId = genreRepository.findById(id)

        if(!checkId.isPresent)
            throw DataNotFoundException("ID Genre Tidak Ada")

        checkId.get().genreName = req.genreName

        val updateGenre= genreRepository.save(checkId.get())
        val resGenreDto = ResGenreDto(
            genreName = updateGenre.genreName
        )
        return ResMessageDto(data = resGenreDto)
    }

    override fun detail(id: Long): ResMessageDto<ResGenreDto> {
        TODO("Not yet implemented")
    }

    override fun list(): ResMessageDto<List<ResGenreDto>> {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long): ResMessageDto<String> {
        TODO("Not yet implemented")
    }
}