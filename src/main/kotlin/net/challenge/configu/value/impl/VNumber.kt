package net.challenge.configu.value.impl

import net.challenge.configu.events.ValueChangeEvent
import net.challenge.configu.value.Value
import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * Default implementation of a [Value] with a Number.
 * All values are stored as [Double].
 */
class VNumber(value: Double) : Value<Double, VNumber>(value) {

    /**
     * The value lower limit.
     * If the value is null there is no limit.
     */
    var minimum: Double? = null
        private set

    /**
     * The value operating limit.
     * If the value is null there is no limit.
     */
    var maximum: Double? = null
        private set

    var decimalPlaces: Int = 1
        private set

    override var value: Double = 0.0
        set(value) {
            // round the number to the specified decimalPlaces
            var roundValue = value.toInt() + (10.0.pow(decimalPlaces) * (value - value.toInt())).roundToInt() / 10.0.pow(decimalPlaces)

            // check minimum limit
            minimum?.let {
                if (value >= it)
                    return@let

                roundValue = it
            }

            // check maximum limit
            maximum?.let {
                if (value <= it)
                    return@let

                roundValue = it
            }

            val event = ValueChangeEvent(field, roundValue)
            changeListener?.onChange(event)

            if (event.cancel) return

            field = event.after
        }

    /**
     * @param value Default value of the number
     * @param minimum Minimum value of the number
     * @param maximum Maximum value of the number
     */
    constructor(value: Number, minimum: Number, maximum: Number) : this(value) {
        this.minimum = minimum.toDouble()
        this.maximum = maximum.toDouble()
    }

    /**
     * @param value Default value of the number
     */
    constructor(value: Number) : this(value.toDouble()) {
        // Is the value a Long there must be no decimal places
        if (value is Int || value is Long || value is Byte || value is Short)
            decimalPlaces = 0
    }

    /**
     * Is [minimum] and [maximum] not null,
     * the number has a distance.
     *
     * @return Has the number a distance.
     */
    fun hasDistance(): Boolean {
        return minimum != null && maximum != null
    }
}