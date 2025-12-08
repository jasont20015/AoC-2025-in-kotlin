import kotlin.math.abs
import kotlin.math.sqrt

fun main() {
    fun parse(input: List<String>) =
        input.map{ Point3D.of(it)}.let { line -> line to line.map{ hashSetOf(it) }.toMutableList() }


    fun Iterable<Long>.product(): Long = reduce { a, b -> a * b }

    fun <T> Iterable<T>.productOf(predicate: (T) -> Long): Long = map(predicate).product()
    fun merge(circuits: MutableList<HashSet<Point3D>>, a: Point3D, b: Point3D): Boolean {
        if (circuits.any { a in it && b in it }) return false
        val ca = circuits.first { a in it && b !in it }
        val cb = circuits.first { b in it && a !in it }
        cb.addAll(ca)
        circuits.remove(ca)
        return true
    }
    fun part1(input: List<String>): Long {
        val (lines, circuits) = parse(input)
        for((first,second) in lines.zipWithAllUnique().sortedBy { (first,second) -> first.distance(second)  }
            .take(lines.size.takeIf { it != 20} ?: 10)) merge(circuits, first, second)
        return circuits.sortedByDescending { it.size }.take(3).productOf { it.size.toLong() }
    }
    
    fun part2(input: List<String>): Long {
        val (lines, circuits) = parse(input)
        val pairs = lines.zipWithAllUnique().sortedBy { (a, b) -> a.distance(b) }.iterator()

        var out = 0L
        while (circuits.size > 1) {
            val (a, b) = pairs.next()
            if (merge(circuits, a, b)) {
                out = a.x.toLong() * b.x.toLong()
            }
        }
        return out
    }

    
    
    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}
