package fr.xiang.tinyurl.repository

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

// timeToLive = 2592000 one month
@RedisHash(value = "TinyUrl", timeToLive = 2592000)
data class TinyUrl(
    @Id val hashValue: String,
    val shortUrl: String,
    val originUrl: String,
)
