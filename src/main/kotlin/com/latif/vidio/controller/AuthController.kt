package com.latif.vidio.controller

import com.latif.vidio.domain.dto.req.ReqLoginDto
import com.latif.vidio.domain.dto.res.ResLoginDto
import com.latif.vidio.domain.dto.res.ResLoginJwtDto
import com.latif.vidio.domain.dto.res.ResMessageDto
import com.latif.vidio.service.AuthService
import com.latif.vidio.util.JWTGenerator
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/auth")
class AuthController (
    val authService: AuthService
){

    @PostMapping("/login")
    fun login(@RequestBody req: ReqLoginDto): ResponseEntity<ResMessageDto<ResLoginDto>> {
        val res = authService.login(req)
        return ResponseEntity.ok(ResMessageDto(
            message = "Success Login",
            data = res
        ))
    }

    @GetMapping("/validate")
    fun validate(@RequestHeader token: String): ResponseEntity<ResMessageDto<ResLoginJwtDto>>{
        val claim = JWTGenerator().decodeJWT(token)
        return ResponseEntity.ok(ResMessageDto(
            message = "Success Decode Jwt",
            data = ResLoginJwtDto(
                claim["id"].toString(),
                claim["userName"].toString(),
                claim["email"].toString(),
                claim["idType"].toString()
            )
        ))
    }

}