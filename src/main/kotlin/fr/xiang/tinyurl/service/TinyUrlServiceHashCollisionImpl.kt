package fr.xiang.tinyurl.service

import fr.xiang.tinyurl.application.dto.TinyUrlRequest
import fr.xiang.tinyurl.application.dto.TinyUrlResDto
import fr.xiang.tinyurl.repository.TinyUrl
import fr.xiang.tinyurl.repository.TinyUrlRepository
import fr.xiang.tinyurl.utils.DateUtils
import org.springframework.stereotype.Service
import java.util.Base64

@Service
class TinyUrlServiceHashCollisionImpl(
    private val tinyUrlRepository: TinyUrlRepository,
) : TinyUrlService {
    // should be in env variable
    private val urlPrefix = "https://my-tiny.test/"

    override fun create(tinyUrlRequest: TinyUrlRequest): ServiceResult<TinyUrlResDto> {
        // try 7 times
        for (i in 0 until 7) {
            val hash = generateString(tinyUrlRequest.originalUrl, i)
            val result = tinyUrlRepository.findById(hash)
            if (result.isPresent) {
                continue
            }
            val tinyUrl = TinyUrl(hash, urlPrefix + hash, tinyUrlRequest.originalUrl, DateUtils.nowLong())
            tinyUrlRepository.save(tinyUrl)
            return ServiceResult.Success(tinyUrl.toTinyUrlResDto())
        }
        return ServiceResult.Failure(ServiceException.CREATION_FAILED)
    }

    override fun getByTinyUrlHash(hash: String): ServiceResult<TinyUrlResDto> {
        val result = tinyUrlRepository.findById(hash)
        return if (result.isPresent) {
            ServiceResult.Success(result.get().toTinyUrlResDtoWithExpiredIn())
        } else {
            ServiceResult.Failure(ServiceException.NOT_FOUND)
        }
    }

    private fun generateString(
        originalUrl: String,
        times: Int,
    ): String {
        val base64Str = Base64.getUrlEncoder().encodeToString(originalUrl.toByteArray())
        return base64Str.substring(times, times + 7)
    }
}
