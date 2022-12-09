package io.github.therealnv6.classic.api

class LocalStorage<T>(val id: String)
{
    val values = mutableListOf<T>()

    fun queried(): QueriedStorage<T>
    {
        return QueriedStorage(this, StorageContainer.queryWrapper)
    }
}

class QueriedStorage<T>(
    val local: LocalStorage<T>,
    val queryWrapper: StorageQueryWrapper,
)
{
    fun store()
    {
        queryWrapper.processMutatingQuery(
            MutatingQuery(local.id, local.values, MutatingQuery.QueryCommand.Store)
        )
    }

    fun load()
    {
        val data = queryWrapper.processFindQuery<T>(
            FindQuery(local.id, FindQuery.QueryCommand.All)
        )

        local.values
            .addAll(data)
    }
}