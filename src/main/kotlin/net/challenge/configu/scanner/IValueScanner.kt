package net.challenge.configu.scanner

import net.challenge.configu.value.Value

/**
 * With the help of a value-scanner you can
 * get all values from a object as a list.
 */
interface IValueScanner {

    /**
     * Find all values in the object.
     */
    fun findValues(obj: Any): List<Value<*>>
}