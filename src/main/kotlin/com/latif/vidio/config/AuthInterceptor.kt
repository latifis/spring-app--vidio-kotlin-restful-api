package com.latif.vidio.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.latif.vidio.domain.dto.res.ResMessageDto
import com.latif.vidio.util.JWTGenerator
import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class AuthInterceptor (
    @Value("\${header.request.api-key}")
    private val apiKey: String
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getHeader("token")

        if(token == null){
            val body: ResMessageDto<String> = ResMessageDto(
                status = "403",
                message = "You don't have permission",
                data = null
            )
            internalServerError(body, response)
            return false
        }


        try {
            JWTGenerator().decodeJWT(token).get("id".toString()) ?: throw RuntimeException("Invalid Token")
        } catch (e: ExpiredJwtException){
            e.printStackTrace()
            val body: ResMessageDto<String> = ResMessageDto("401", "Invalid Token", null)
            internalServerError(body, response)
            return false
        }

        return super.preHandle(request, response, handler)
    }

    fun internalServerError(body: ResMessageDto<String>, response: HttpServletResponse): HttpServletResponse{
        response.status = HttpStatus.FORBIDDEN.value()
        response.contentType = "application/json"
        response.writer.write(convertObjectToJson(body))

        return response
    }

    fun convertObjectToJson(dto: ResMessageDto<String>): String {
        return ObjectMapper().writeValueAsString(dto)
    }
}