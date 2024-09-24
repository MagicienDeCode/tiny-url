package fr.xiang.tinyurl.service

import fr.xiang.tinyurl.application.dto.TinyUrlResDto
import fr.xiang.tinyurl.repository.TinyUrl
import fr.xiang.tinyurl.utils.DateUtils
import fr.xiang.tinyurl.utils.EXPIRED_IN

fun TinyUrl.toTinyUrlResDto() =
    with(this) {
        TinyUrlResDto(
            shortUrl = shortUrl,
            originUrl = originUrl,
        )
    }

fun TinyUrl.toTinyUrlResDtoWithExpiredIn() =
    with(this) {
        TinyUrlResDto(
            shortUrl = shortUrl,
            originUrl = originUrl,
            expiredIn = EXPIRED_IN - (DateUtils.nowLong() - createdAt)
        )
    }
