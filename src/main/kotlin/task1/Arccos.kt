package task1

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.PI

class Arccos(val arg: Double, val iterationCounter: Int) {

    fun factorial(number: Int): Double = if(number < 2) 1.0 else number * factorial(number - 1)

    fun arrcos_teylor(): Double {
        var result: Double = PI / 2
        if(arg == 1.0) return 0.0
        else if (arg == -1.0) return Math.PI
        else if (abs(arg) > 1 || arg.isNaN()) throw IllegalArgumentException("Аргумент должен быть в диапазоне [-1; 1]")
        else {

            for (n in 0 until iterationCounter) {
                val coef: Double = factorial(2 * n) / (4.0.pow(n) * (factorial(n).pow(2)) * (2 * n + 1))

                val termValue = coef * arg.pow(2 * n + 1)
                if (abs(termValue) < 1e-15) break

                result -= termValue
            }
        }
        return result

    }
}
