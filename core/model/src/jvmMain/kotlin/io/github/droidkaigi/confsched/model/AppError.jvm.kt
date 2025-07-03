package io.github.droidkaigi.confsched.model

actual public fun knownPlatformExceptionOrNull(e: Throwable): AppError? {
    return if (e is java.net.UnknownHostException) {
        AppError.InternetConnectionException(e)
    } else {
        null
    }
}
