package fr.xiang.tinyurl.repository

import fr.xiang.tinyurl.utils.EXPIRED_IN
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

// timeToLive = 2592000 one month
@RedisHash(value = "TinyUrl", timeToLive = EXPIRED_IN)
data class TinyUrl(
    @Id val hashValue: String,
    val shortUrl: String,
    val originUrl: String,
    val createdAt: Long
)
