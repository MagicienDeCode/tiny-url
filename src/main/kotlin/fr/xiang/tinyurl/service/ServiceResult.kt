package fr.xiang.tinyurl.service

sealed class ServiceResult<out T> {
    data class Success<out T>(val value: T) : ServiceResult<T>()

    data class Failure(
        val serviceException: ServiceException,
        val cause: Throwable? = null,
    ) : ServiceResult<Nothing>() {
        val code
            get() = serviceException.code
        val message
            get() = serviceException.message
    }
}
