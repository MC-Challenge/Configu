package net.challenge.configu.scanner

import net.challenge.configu.container.ISaveContainer
import net.challenge.configu.value.VTag
import net.challenge.configu.value.Value
import java.lang.reflect.Field
import java.util.ArrayList

/**
 * # ValueScanner
 * Using the [ValueScanner] you can find all
 * values in a [ISaveContainer] and return as a [List].
 */
object ValueScanner : IValueScanner{

    /**
     * The method searches for values in the object and the superclasses of the object.
     *
     * @param obj Object to be searched for values
     */
    override fun findValues(obj: Any): List<Value<*, *>> {
        val values = ArrayList<Value<*, *>>()
        var currentClass: Class<*>? = obj.javaClass

        do {
            if (currentClass!!.name == "java.lang.Object") break

            values.addAll(findValues(obj, currentClass))

            currentClass = currentClass.superclass
        } while (currentClass != null)

        return values
    }

    /**
     * The method searches for values in the class of the handler.
     *
     * @param obj Object to scanning.
     * @param clazz Class to search for values.
     *
     * @return List of all founded values.
     */
    private fun findValues(obj: Any, clazz: Class<*>): List<Value<*, *>> {
        return clazz.declaredFields
            .toList()
            .filter { hasTag(it) }
            .map { field -> fieldToValue(obj, field) }

    }

    /**
     * Get from the object the [Value]
     *
     * @param obj The obj in which the value is.
     * @param field The field from the value.
     *
     * @return The Value from the field
     */
    private fun fieldToValue(obj: Any, field: Field): Value<*, *> {
        val value = getField(obj, field) ?: throw RuntimeException("Value is Null")

        if (value !is Value<*, *>)
            throw RuntimeException("The field is not a Value")

        val tag = getTag(field)!!

        value.name = tag.name
        value.description = tag.description

        return value
    }

    /**
     * Gets the [Value] of a field from an Object.
     *
     * @param obj Object that field belongs to.
     * @param field  Field that is being retrieved.
     *
     * @return The value of the field
     */
    private fun getField(obj: Any, field: Field): Any? {
        var value: Any? = null

        try {
            val accessible = field.isAccessible
            field.isAccessible = true
            value = field[obj]
            field.isAccessible = accessible
        } catch (ignored: NullPointerException) {
        } catch (ignored: IllegalAccessException) {
        }

        return value
    }

    /**
     * Returns whether or not the specified field
     * has a [VTag] annotation or not.
     *
     * @param field Field being checked
     * @return If the field has a [VTag] annotation
     */
    private fun hasTag(field: Field): Boolean {
        return getTag(field) != null
    }

    /**
     * Gets the class of the value annotation belonging
     * to a field, null if there is none
     *
     * @param field Field being checked
     * @return The [VTag] annotation of the field
     */
    private fun getTag(field: Field): VTag? {
        return field.getAnnotation(VTag::class.java)
    }
}