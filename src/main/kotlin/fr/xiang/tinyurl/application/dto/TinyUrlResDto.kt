package fr.xiang.tinyurl.application.dto

data class TinyUrlResDto(
    val shortUrl: String,
    val originUrl: String,
    val expiredIn: Long = 2592000,
)
