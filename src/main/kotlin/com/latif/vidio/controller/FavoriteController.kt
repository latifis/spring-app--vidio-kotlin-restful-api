package com.latif.vidio.controller

import com.latif.vidio.domain.dto.req.ReqFavoriteDto
import com.latif.vidio.domain.dto.res.ResMessageDto
import com.latif.vidio.domain.dto.res.ResFavoriteDto
import com.latif.vidio.service.FavoriteService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/favorite")
class FavoriteController (
    val favoriteService: FavoriteService
){

    @PostMapping()
    fun insert(
        @Valid
        @RequestBody req: ReqFavoriteDto
    ): ResponseEntity<ResMessageDto<ResFavoriteDto>> {
        val response = favoriteService.insert(req)
        return ResponseEntity.ok(response)
    }

    @PutMapping()
    fun update(
        @Valid
        @RequestBody req: ReqFavoriteDto,
        @RequestParam id: Long
    ): ResponseEntity<ResMessageDto<ResFavoriteDto>> {
        val response = favoriteService.update(id, req)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/detail")
    fun detail(
        @RequestParam id: Long
    ): ResponseEntity<ResMessageDto<ResFavoriteDto>> {
        val response = favoriteService.detail(id)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/list")
    fun list(): ResponseEntity<ResMessageDto<List<ResFavoriteDto>>> {
        val response = favoriteService.list()
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam id: Long): ResponseEntity<ResMessageDto<String>> {
        val res = favoriteService.delete(id)
        return ResponseEntity.ok(res)
    }

}