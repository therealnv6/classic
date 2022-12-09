package io.github.therealnv6.classic.rest.client

import io.github.therealnv6.classic.api.FindQuery
import io.github.therealnv6.classic.api.MutatingQuery
import io.github.therealnv6.classic.api.StorageQueryWrapper

class RestStorageQueryWrapper : StorageQueryWrapper
{
    override fun <T> processMutatingQuery(query: MutatingQuery<T>)
    {
        TODO("Not yet implemented")
    }

    override fun <T> processFindQuery(query: FindQuery<T>): Iterable<T>
    {
        TODO("Not yet implemented")
    }
}