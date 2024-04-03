package com.latif.vidio.domain.dto.req

import com.latif.vidio.domain.entity.FavoriteEntity
import com.latif.vidio.domain.entity.TypeUserEntity
import jakarta.persistence.*

data class ReqUserDto(

    val userName: String? = null,

    val email: String? = null,

    val password: String? = null,

    val idType: String? = null

)
//    @field:NotBlank(message = "Nama tidak boleh kosong")
//    @field:Pattern(regexp = "^[a-zA-Z ]*\$", message = "Hanya huruf yang diperbolehkan")
//    @field:Size(max = 100, message = "Nama tidak boleh lebih dari 100 karakter")
//    val name: String?,
//
//    @field:NotEmpty(message = "Username tidak boleh kosong")
//    @field:Size(max = 32, message = "Username tidak boleh lebih dari 32 karakter")
//    @field:Pattern(regexp = "^[^\\s]*\$", message = "Username tidak boleh ada spasi")
//    val username: String?,
//
//    @field:NotBlank(message = "Email tidak boleh kosong")
//    @field:Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$", message = "Alamat email tidak valid")
//    val email: String?,
//
//    @field:Size(max = 32, message = "Password tidak boleh lebih dari 32 karakter")
//    val password: String?