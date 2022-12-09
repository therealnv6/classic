package io.github.therealnv6.classic.shared

val converters = hashMapOf<String, BridgeConverter<*>>()

fun injectBridgeIds(instance: Any)
{
    val clazz = instance::class
    val fields = clazz.java.declaredFields

    for (field in fields)
    {
        if (field.type != BridgeMarker::class.java && !field.isAnnotationPresent(BridgedTypeId::class.java))
        {
            continue
        }

        val typeId = field
            .getDeclaredAnnotationsByType(BridgedTypeId::class.java)
            .first()

        val current = field[instance] as BridgeMarker

        current.injectBridgedId(typeId)
    }
}

class BridgeMarker(val value: String)
{
    lateinit var id: BridgedTypeId

    inline fun <reified T> asBridgedType(): BridgedType<T>?
    {
        return converters[id.value]?.intoBridgedType(this)
                as BridgedType<T>?
    }

    fun injectBridgedId(id: BridgedTypeId)
    {
        this.id = id
    }
}

interface BridgeConverter<T>
{
    fun intoBridgedType(marker: BridgeMarker): BridgedType<T>
}

interface BridgedType<T>
{
    fun into(value: String): T?
    fun from(value: T): String?
}

annotation class BridgedTypeId(val value: String)