package com.latif.vidio.controller

import com.latif.vidio.domain.dto.req.ReqVidioDto
import com.latif.vidio.domain.dto.res.ResMessageDto
import com.latif.vidio.domain.dto.res.ResVidioDto
import com.latif.vidio.service.VidioService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/vidio")
class VidioController (
    val vidioService: VidioService
){

    @PostMapping()
    fun insert(
        @Valid
        @RequestBody req: ReqVidioDto,
        @RequestParam type: String? = null
    ): ResponseEntity<ResMessageDto<ResVidioDto>> {
        val response = vidioService.insert(type, req)
        return ResponseEntity.ok(response)
    }

    @PutMapping()
    fun update(
        @Valid
        @RequestBody req: ReqVidioDto,
        @RequestParam id: Long
    ): ResponseEntity<ResMessageDto<ResVidioDto>> {
        val response = vidioService.update(id, req)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/detail")
    fun detail(
        @RequestParam id: Long
    ): ResponseEntity<ResMessageDto<ResVidioDto>> {
        val response = vidioService.detail(id)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/list")
    fun list(
        @RequestParam name: String? = null,
        @RequestParam creator: String? = null
    ): ResponseEntity<ResMessageDto<List<ResVidioDto>>> {
        val response = vidioService.list(name, creator)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam id: Long): ResponseEntity<ResMessageDto<String>> {
        val res = vidioService.delete(id)
        return ResponseEntity.ok(res)
    }

}