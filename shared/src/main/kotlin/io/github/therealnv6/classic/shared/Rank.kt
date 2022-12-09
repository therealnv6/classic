package io.github.therealnv6.classic.shared

class Rank(val id: String)
{
    @BridgedTypeId("chat_color")
    val color = BridgeMarker("WHITE")

    val prefix = Text.into("&f")
    val suffix = Text.into("&f")
}

class Text @Deprecated("Use Text.into(String) instead of the constructor.") constructor(val parts: List<TextPart>)
{
    companion object
    {
        private const val COLOR_IDENTIFIER = '&';
        private val VALID_COLOR_IDS = listOf(
            '0', '1', '2',
            '3', '4', '5',
            '6', '7', '8',
            '9', 'a', 'b',
            'c', 'd', 'e',
            'f', 'l', 'o'
        )

        fun into(string: String): Text
        {
            if (!string.contains('&'))
            {
                return Text(
                    listOf(TextPart(string, TextPartType.Text))
                )
            }

            val array = string.toCharArray()
            val parts = mutableListOf<TextPart>()

            var currentValue = ""

            for (iterable in array.withIndex())
            {
                val char = iterable.value
                val index = iterable.index

                if (array[index - 1] == '&' && VALID_COLOR_IDS.contains(char))
                {
                    currentValue = currentValue.dropLast(1)

                    if (currentValue.isNotEmpty())
                    {
                        parts.add(
                            TextPart(currentValue, TextPartType.Text)
                        )
                    }

                    currentValue = ""
                    parts.add(
                        TextPart("" + array[index - 1] + char, TextPartType.Color)
                    )
                }
            }

            return Text(parts)
        }
    }

    class TextPart(
        val value: String,
        val type: TextPartType
    )

    enum class TextPartType
    {
        Color,
        Text
    }
}