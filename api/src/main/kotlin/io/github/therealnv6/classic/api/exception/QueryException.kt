package io.github.therealnv6.classic.api.exception

open class QueryException(message: String) : Exception(message)
open class UnreachableApiException(message: String) : QueryException(message)