package fr.xiang.tinyurl.service

import fr.xiang.tinyurl.application.dto.TinyUrlRequest
import fr.xiang.tinyurl.application.dto.TinyUrlResDto

interface TinyUrlService {
    fun create(tinyUrlRequest: TinyUrlRequest): ServiceResult<TinyUrlResDto>

    fun getByTinyUrlHash(hash: String): ServiceResult<TinyUrlResDto>
}
