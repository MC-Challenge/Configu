package net.challenge.configu.value.impl

import net.challenge.configu.value.Value
import java.awt.Color

/**
 * Default implementation of a [Value] with a [Color].
 */
class VColor(value: Color) : Value<Color>(value) {

    /**
     * Get the color as hex-string.
     */
    fun getAsHex(): String {
        val buf = Integer.toHexString(value.rgb)
        return "#" + buf.substring(buf.length - 6)
    }
}