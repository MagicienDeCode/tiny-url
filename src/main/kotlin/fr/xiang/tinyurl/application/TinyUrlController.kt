package fr.xiang.tinyurl.application

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import fr.xiang.tinyurl.application.dto.TinyUrlRequest
import fr.xiang.tinyurl.configuration.lazyLogger
import fr.xiang.tinyurl.service.ServiceResult
import fr.xiang.tinyurl.service.TinyUrlService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.badRequest
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/tiny-url")
class TinyUrlController(
    @Qualifier("tinyUrlServiceBase62Impl") private val tinyUrlService: TinyUrlService
) {
    private val logger by lazyLogger()
    private val jacksonMapper = jacksonObjectMapper()

    @PostMapping
    fun create(
        @RequestBody tinyUrlRequest: TinyUrlRequest,
    ): ResponseEntity<out Any> {
        return when (val result = tinyUrlService.create(tinyUrlRequest)) {
            is ServiceResult.Success -> ok().body(result.value)
            is ServiceResult.Failure -> badRequest().body(jacksonMapper.writeValueAsString(result.serviceException))
        }
    }

    @GetMapping("/{hash}")
    fun redirect(
        @PathVariable hash: String,
    ): ResponseEntity<out Any> {
        return when (val result = tinyUrlService.getByTinyUrlHash(hash)) {
            is ServiceResult.Success -> ok().body(result.value)
            is ServiceResult.Failure -> badRequest().body(jacksonMapper.writeValueAsString(result.serviceException))
        }
    }
}
