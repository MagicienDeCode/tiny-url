package fr.xiang.tinyurl.service

import fr.xiang.tinyurl.application.dto.TinyUrlResDto
import fr.xiang.tinyurl.repository.TinyUrl

fun TinyUrl.toTinyUrlResDto() =
    with(this) {
        TinyUrlResDto(
            shortUrl = shortUrl,
            originUrl = originUrl,
        )
    }
