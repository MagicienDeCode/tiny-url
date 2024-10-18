package fr.xiang.tinyurl.service

import fr.xiang.tinyurl.application.dto.TinyUrlRequest
import fr.xiang.tinyurl.application.dto.TinyUrlResDto
import fr.xiang.tinyurl.repository.TinyUrl
import fr.xiang.tinyurl.repository.TinyUrlRepository
import fr.xiang.tinyurl.utils.DateUtils
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.function.LongBinaryOperator

@Service
class TinyUrlServiceBase62Impl(
    private val tinyUrlRepository: TinyUrlRepository,
) : TinyUrlService {
    // should be in env variable
    private val urlPrefix = "https://my-tiny.test/"
    private final val accumulator = LongBinaryOperator { currentValue, x -> currentValue + x }

    init {
        val total = tinyUrlRepository.findAll().count().toLong()
        count.accumulateAndGet(total, accumulator)
    }

    companion object {
        // should create a new service to generate cross-server unique id
        var count = AtomicLong(0L)
    }

    override fun create(tinyUrlRequest: TinyUrlRequest): ServiceResult<TinyUrlResDto> {
        // multi instance, concurrent problems
        val hash = Base62Converter.encode(count.accumulateAndGet(1, accumulator))
        val tinyUrl = TinyUrl(hash, urlPrefix + hash, tinyUrlRequest.originalUrl, DateUtils.nowLong())
        tinyUrlRepository.save(tinyUrl)
        return ServiceResult.Success(tinyUrl.toTinyUrlResDto())
    }

    override fun getByTinyUrlHash(hash: String): ServiceResult<TinyUrlResDto> {
        val result = tinyUrlRepository.findById(hash)
        return if (result.isPresent) {
            ServiceResult.Success(result.get().toTinyUrlResDtoWithExpiredIn())
        } else {
            ServiceResult.Failure(ServiceException.NOT_FOUND)
        }
    }
}