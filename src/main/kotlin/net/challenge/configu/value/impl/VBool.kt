package net.challenge.configu.value.impl

import net.challenge.configu.value.Value

/**
 * Default implementation of a [Value] with a [Boolean].
 */
class VBool(value: Boolean) : Value<Boolean, VBool>(value) {

    /**
     * Switch the value.
     */
    fun toggle() {
        this.value = !value
    }
}