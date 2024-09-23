package fr.xiang.tinyurl.service

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ServiceException(val code: Int, val message: String) {
    NOT_FOUND(1001, "Not found or expired"),
    CREATION_FAILED(1002, "Failed to create tiny url"),
}
