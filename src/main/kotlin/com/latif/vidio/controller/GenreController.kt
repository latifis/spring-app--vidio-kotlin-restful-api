package com.latif.vidio.controller

import com.latif.vidio.domain.dto.req.ReqGenreDto
import com.latif.vidio.domain.dto.res.ResMessageDto
import com.latif.vidio.domain.dto.res.ResGenreDto
import com.latif.vidio.service.GenreService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/genre")
class GenreController (
    val genreService: GenreService
){

    @PostMapping()
    fun insert(
        @Valid
        @RequestBody req: ReqGenreDto
    ): ResponseEntity<ResMessageDto<ResGenreDto>> {
        val response = genreService.insert(req)
        return ResponseEntity.ok(response)
    }

    @PutMapping()
    fun update(
        @Valid
        @RequestBody req: ReqGenreDto,
        @RequestParam id: Long
    ): ResponseEntity<ResMessageDto<ResGenreDto>> {
        val response = genreService.update(id, req)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/detail")
    fun detail(
        @RequestParam id: Long
    ): ResponseEntity<ResMessageDto<ResGenreDto>> {
        val response = genreService.detail(id)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/list")
    fun list(): ResponseEntity<ResMessageDto<List<ResGenreDto>>> {
        val response = genreService.list()
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam id: Long): ResponseEntity<ResMessageDto<String>> {
        val res = genreService.delete(id)
        return ResponseEntity.ok(res)
    }

}