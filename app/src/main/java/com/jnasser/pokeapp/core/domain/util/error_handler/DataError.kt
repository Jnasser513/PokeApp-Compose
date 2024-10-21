package com.jnasser.pokeapp.core.domain.util.error_handler

sealed interface DataError: Error {

    enum class Network: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUEST,
        NO_INTERNET,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN
    }
}