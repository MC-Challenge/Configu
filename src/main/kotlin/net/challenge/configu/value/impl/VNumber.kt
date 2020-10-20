package net.challenge.configu.value.impl

import net.challenge.configu.value.Value
import kotlin.math.pow
import kotlin.math.roundToInt

class VNumber(value: Double) : Value<Double>(value) {

    var minimum: Double? = null
        private set

    var maximum: Double? = null
        private set

    var decimalPlaces: Int = 1
        private set

    override var value: Double = 0.0
        set(value) {
            val roundValue = value.toInt() + (10.0.pow(decimalPlaces) * (value - value.toInt())).roundToInt() / 10.0.pow(decimalPlaces)

            minimum?.let {
                if (value >= it)
                    return@let

                field = it
                return
            }

            maximum?.let {
                if (value <= it)
                    return@let

                field = it
                return
            }

            field = roundValue
        }

    constructor(value: Long) : this(value.toDouble()) {
        decimalPlaces = 0
    }

    constructor(value: Int) : this(value.toLong())

    constructor(value: Float) : this(value.toDouble())

    fun setDistance(minimum: Double, maximum: Double): VNumber {
        this.minimum = minimum
        this.maximum = maximum
        return this
    }

    fun setDistance(minimum: Int, maximum: Int): VNumber {
        return setDistance(minimum.toDouble(), maximum.toDouble())
    }

    fun setDistance(minimum: Float, maximum: Float): VNumber {
        return setDistance(minimum.toDouble(), maximum.toDouble())
    }

    fun setMinimum(minimum: Double): VNumber {
        this.minimum = minimum
        return this
    }

    fun setMaximum(maximum: Double): VNumber {
        this.maximum = maximum
        return this
    }

    fun setDecimalPlaces(decimalPlaces: Int): VNumber {
        this.decimalPlaces = decimalPlaces
        return this
    }
}