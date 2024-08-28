import kotlin.math.absoluteValue

class FractionMutable(numerator: Int, denominator: Int = 1, sign: Int = 1) {
    private var numerator: Int = numerator * sign
    private var denominator: Int = denominator

    init {
        if (denominator == 0) {
            throw IllegalArgumentException("Denominator cannot be zero.")
        }
        simplify()
    }

    override fun toString(): String {
        val signString = if (numerator < 0) "-" else ""
        return "$signString${numerator.absoluteValue}/${denominator.absoluteValue}"
    }

    fun negate() {
        numerator *= -1
    }

    fun add(other: FractionMutable) {
        val commonDenominator = lcm(denominator, other.denominator)
        val newNumerator = numerator * (commonDenominator / denominator) + other.numerator * (commonDenominator / other.denominator)
        numerator = newNumerator
        denominator = commonDenominator
        simplify()
    }

    fun mult(other: FractionMutable) {
        numerator *= other.numerator
        denominator *= other.denominator
        simplify()
    }

    fun div(other: FractionMutable) {
        if (other.numerator == 0) {
            throw IllegalArgumentException("Cannot divide by zero.")
        }
        numerator *= other.denominator
        denominator *= other.numerator
        simplify()
    }

    fun intPart(): Int {
        return numerator / denominator
    }

    private fun simplify() {
        val gcd = gcd(numerator.absoluteValue, denominator.absoluteValue)
        numerator /= gcd
        denominator /= gcd
        if (denominator < 0) {
            numerator = -numerator
            denominator = -denominator
        }
    }

    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }

    private fun lcm(a: Int, b: Int): Int {
        return (a * b) / gcd(a, b)
    }
}
