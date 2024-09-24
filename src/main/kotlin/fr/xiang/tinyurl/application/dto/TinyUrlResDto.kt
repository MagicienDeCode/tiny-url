package fr.xiang.tinyurl.application.dto

import fr.xiang.tinyurl.utils.EXPIRED_IN

data class TinyUrlResDto(
    val shortUrl: String,
    val originUrl: String,
    val expiredIn: Long = EXPIRED_IN,
)
