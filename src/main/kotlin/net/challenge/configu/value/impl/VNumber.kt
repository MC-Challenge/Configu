package net.challenge.configu.value.impl

import net.challenge.configu.value.Value
import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * Default implementation of a [Value] with a Number.
 * All values are stored as [Double].
 */
class VNumber(value: Double) : Value<Double>(value) {

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
            val roundValue = value.toInt() + (10.0.pow(decimalPlaces) * (value - value.toInt())).roundToInt() / 10.0.pow(decimalPlaces)

            // check minimum limit
            minimum?.let {
                if (value >= it)
                    return@let

                field = it
                return
            }

            // check maximum limit
            maximum?.let {
                if (value <= it)
                    return@let

                field = it
                return
            }

            field = roundValue
        }

    constructor(value: Long) : this(value.toDouble()) {
        // Is the value a Long there must be no decimal places
        decimalPlaces = 0
    }

    constructor(value: Int) : this(value.toLong())

    constructor(value: Float) : this(value.toDouble())

    /**
     * Is [minimum] and [maximum] not null,
     * the number has a distance.
     *
     * @return Has the number a distance.
     */
    fun hasDistance(): Boolean {
        return minimum != null && maximum != null
    }

    /**
     * Set the distance of the number.
     *
     * @param minimum Minimum to set.
     * @param maximum Maximum to set.
     */
    fun setDistance(minimum: Double, maximum: Double): VNumber {
        this.minimum = minimum
        this.maximum = maximum
        return this
    }

    /**
     * Set the distance of the number.
     *
     * @param minimum Minimum to set.
     * @param maximum Maximum to set.
     */
    fun setDistance(minimum: Int, maximum: Int): VNumber {
        return setDistance(minimum.toDouble(), maximum.toDouble())
    }

    /**
     * Set the distance of the number.
     *
     * @param minimum Minimum to set.
     * @param maximum Maximum to set.
     */
    fun setDistance(minimum: Float, maximum: Float): VNumber {
        return setDistance(minimum.toDouble(), maximum.toDouble())
    }

    /**
     * Set the minimum of the number.
     *
     * @param minimum Minimum to set.
     */
    fun setMinimum(minimum: Double): VNumber {
        this.minimum = minimum
        return this
    }

    /**
     * Set the maximum of the number.
     *
     * @param maximum Maximum to set.
     */
    fun setMaximum(maximum: Double): VNumber {
        this.maximum = maximum
        return this
    }

    /**
     * Set the maximum decimalPlaces of the number.
     *
     * @param decimalPlaces Decimal-Places to set.
     */
    fun setDecimalPlaces(decimalPlaces: Int): VNumber {
        this.decimalPlaces = decimalPlaces
        return this
    }
}