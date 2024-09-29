package fr.xiang.tinyurl.service

object Base62Converter {

    private const val BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
    private const val BASE = 62

    fun encode(value: Long): String {
        if (value == 0L) return "0"

        var num = value
        val sb = StringBuilder()

        while (num > 0) {
            sb.append(BASE62[(num % BASE).toInt()])
            num /= BASE
        }

        return sb.reverse().toString()
    }

    fun decode(base62: String): Long {
        var result = 0L

        for (char in base62) {
            result = result * BASE + BASE62.indexOf(char)
        }

        return result
    }
}