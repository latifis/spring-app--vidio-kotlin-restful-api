package com.latif.vidio.controller

import com.latif.vidio.domain.dto.req.ReqUserDto
import com.latif.vidio.domain.dto.res.ResMessageDto
import com.latif.vidio.domain.dto.res.ResUserDto
import com.latif.vidio.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/user")
class UserController (
    val userService: UserService
){

    @PostMapping()
    fun insert(
        @Valid
        @RequestBody req: ReqUserDto
    ): ResponseEntity<ResMessageDto<ResUserDto>> {
        val response = userService.insert(req)
        return ResponseEntity.ok(response)
    }

    @PutMapping()
    fun update(
        @Valid
        @RequestBody req: ReqUserDto,
        @RequestParam id: Long
    ): ResponseEntity<ResMessageDto<ResUserDto>> {
        val response = userService.update(id, req)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/detail")
    fun detail(
        @RequestParam id: Long
    ): ResponseEntity<ResMessageDto<ResUserDto>> {
        val response = userService.detail(id)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/list")
    fun list(): ResponseEntity<ResMessageDto<List<ResUserDto>>> {
        val response = userService.list()
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam id: Long): ResponseEntity<ResMessageDto<String>> {
        val res = userService.delete(id)
        return ResponseEntity.ok(res)
    }
}