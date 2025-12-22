fun main() {
    fun part1(input: List<String>): Int {
        val inputJoined = input.joinToString("\n")
        val inputSplit = inputJoined.split("\n\n")
        val sectionCountMap = mutableMapOf<Int, Int>()
        var count = 0
        inputSplit.forEachIndexed { index, section ->
            if(index == 6){
                return@forEachIndexed
            }
            val count = section.count { it == '#' }
            sectionCountMap[index] = count

        }
        val checkValues = inputSplit[6].split("\n")
        checkValues.forEach {
            val size = it.split(":")[0]
            val nums = it.split(":")[1].split(" ").filter { it != "" }
            val numsAsInt = nums.map{ it.toInt() }
            val TileNums = size.split("x")[0].toInt() * size.split("x")[1].toInt()
            var used = 1
            numsAsInt.forEachIndexed { index, int ->
                used += sectionCountMap.getOrDefault(index, 0) * int
            }
            if (used <= TileNums){
                count += 1
            }
        }
        return count
    }
    
    fun part2(input: List<String>): Int {
        return input.size
    }
    
    val input = readInput("Day12")
    part1(input).println()
    part2(input).println()
}
