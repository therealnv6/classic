package io.github.therealnv6.classic.api

interface StorageQueryWrapper
{
    fun <T> processMutatingQuery(query: MutatingQuery<T>)
    fun <T> processFindQuery(query: FindQuery<T>): Iterable<T>
}

class MutatingQuery<T>(
    val parent: String,
    val data: Iterable<T>,
    val command: QueryCommand,
)
{
    enum class QueryCommand
    {
        Store,
        Delete,
    }
}

class FindQuery<T>(val parent: String, command: QueryCommand)
{
    enum class QueryCommand
    {
        All,
    }
}