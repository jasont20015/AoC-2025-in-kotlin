import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("resources/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun Long.length() = when(this) {
    0L -> 1
    else -> log10(abs(toDouble())).toInt() + 1
}

data class Point3D(var x: Int, var y: Int, var z: Int): Comparable<Point3D> {
    infix fun distance(other: Point3D) = sqrt(abs((x - other.x)).toDouble().pow(2) + abs((y - other.y)).toDouble().pow(2) + abs((z - other.z)).toDouble().pow(2))

    override operator fun compareTo(other: Point3D) = compareValuesBy(this, other, Point3D::x, Point3D::y, Point3D::z)
    companion object {
        val ORIGIN = Point3D(0, 0, 0)
        fun of(input: String): Point3D {
            val (x, y, z) = input.split(',', '-', ' ').map { it.trim().toInt() }
            return Point3D(x, y, z)
        }
    }
}
fun <T> List<T>.zipWithAllUnique(): List<Pair<T, T>> {
    val result = mutableListOf<Pair<T, T>>()
    val seenPairs = mutableSetOf<Pair<T, T>>()

    for (i in this.indices) {
        for (j in i + 1 until this.size) {
            val pair = Pair(this[i], this[j])
            if (pair !in seenPairs) {
                result.add(pair)
                seenPairs.add(pair)
                seenPairs.add(pair.copy(first = pair.second, second = pair.first)) // Add reverse pair as well
            }
        }
    }
    return result
}