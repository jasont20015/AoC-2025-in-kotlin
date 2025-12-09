import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.Envelope
import org.locationtech.jts.geom.GeometryFactory
import kotlin.math.abs
import kotlin.math.max

fun main() {
    data class Pos(val x: Int, val y: Int){
        fun area(pos2: Pos): Long{
            return (abs(pos2.x-this.x) + 1).toLong() * (abs(pos2.y-this.y) + 1).toLong()
        }
    }

    fun part1(input: List<String>): Long {
        val coordinates = mutableListOf<Pos>()
        for(line in input){
            val values = line.split(",")
            val position = Pos(values[0].toInt(), values[1].toInt())
            coordinates.add(position)
        }
        var biggestArea = 0L
        for(i in 0..coordinates.size) {
            for (j in i..<coordinates.size){
                biggestArea = max(biggestArea, coordinates[i].area(coordinates[j]))
            }
        }
        return biggestArea
    }
    
    fun part2(input: List<String>): Long {
        val coordinates = mutableListOf<Coordinate>()
        for(line in input){
            val values = line.split(",")
            val position = Coordinate(values[0].toDouble(), values[1].toDouble())
            coordinates.add(position)
        }
        coordinates.add(coordinates[0])
        val coordinateArr = coordinates.toTypedArray()
        val factory = GeometryFactory()
        val poly = factory.createPolygon(coordinateArr)
        var biggestArea = 0L
        for (i in 0..<coordinates.size){
            val start = coordinates[i]
            for(j in 0..< coordinates.size) {
                if (i == j) {
                    continue
                }
                val end = coordinates[j]
                val envelope = Envelope(start, end)
                val subset = factory.toGeometry(envelope)
                if(subset.within(poly)){
                    val area = (abs(start.x - end.x) + 1).toLong() * (abs(start.y - end.y) + 1).toLong()
                    biggestArea = max(biggestArea, area)
                }
            }
        }
        return biggestArea
    }
    
    val testInput = readInput("Day09_test")
    check(part2(testInput) == 24L)
    
    
    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}
