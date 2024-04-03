package com.latif.vidio.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class PathMatchingInterceptor (
    val authInterceptor: AuthInterceptor,
    val requestInterceptor: RequestInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(requestInterceptor)

        registry.addInterceptor(authInterceptor).excludePathPatterns(
//            "/v1/api/login"
            "/v1/api/auth/login"
        )
    }

}