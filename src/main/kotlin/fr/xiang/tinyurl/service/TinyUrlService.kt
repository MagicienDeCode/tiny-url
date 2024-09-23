package fr.xiang.tinyurl.service

import fr.xiang.tinyurl.application.dto.TinyUrlRequest
import fr.xiang.tinyurl.application.dto.TinyUrlResDto

interface TinyUrlService {
    fun create(tinyUrlRequest: TinyUrlRequest): ServiceResult<TinyUrlResDto>

    fun getByTinyUrl(shortUrl: String): ServiceResult<TinyUrlResDto>
}
