package com.latif.vidio.exception

import com.latif.vidio.domain.dto.res.ResMessageDto
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ErrorHandler {

//    @ExceptionHandler(MissingServletRequestParameterException::class)
//    fun handleMissingServletRequestParameterException(exception: MissingServletRequestParameterException): ResponseEntity<Any> {
//        val errorMessage = "Required request parameter '${exception.parameterName}' for method parameter type ${exception.parameterType} is not present"
//        val result = mapOf<String, Any>("status" to "F", "error" to "parameter", "message" to errorMessage)
//        return ResponseEntity.badRequest().body(result)
//    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleArgumentNotValidException(exception: MethodArgumentNotValidException) : ResponseEntity<Any>{
        val errors = mutableListOf<String>()
        exception.bindingResult.fieldErrors.forEach {
            errors.add(it.defaultMessage!!)
        }
        val result = mapOf<String, Any>("status" to "F", "error" to "field", "message" to errors)
        return ResponseEntity.badRequest().body(result)
    }

    @ExceptionHandler(DataNotFoundException::class)
    fun handleDataNotFound(exception: RuntimeException) : ResponseEntity<ResMessageDto<*>>{
        exception.printStackTrace()
        return ResponseEntity.badRequest().body(ResMessageDto<Any?>(
            status = "F",
            message = exception.message.toString()
        ))
    }

    @ExceptionHandler(DataExist::class)
    fun handleDataExist(exception: RuntimeException) : ResponseEntity<ResMessageDto<*>>{
        exception.printStackTrace()
        return ResponseEntity.badRequest().body(
            ResMessageDto<Any?>(
            status = "F",
            message = exception.message.toString()
        )
        )
    }

}