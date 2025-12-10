fun main() {
    fun part1(input: List<String>): Int {
        var ans = 0
        for(line in input){
            val parts = line.split(" ")
            val end = parts[0].trim('[',']')
            val buttons  = parts.subList(1, parts.size - 1).map {
                it.trim('(', ')').split(",").map { num -> num.toInt() }
            }
            val final = end.substring(0, end.length).map {it == '#'}
            val state = MutableList(final.size) {false}

            val queue = ArrayDeque<Pair<List<Boolean>, Int>>()
            queue.add(Pair(state, 0))
            val visited = mutableSetOf(state.toString())
            while (queue.isNotEmpty()){
                val (currentState, depth) = queue.removeFirst()

                if(currentState == final){
                    ans += depth
                    break
                }

                for(button in buttons) {
                    val newState = currentState.toMutableList()
                    for(press in button){
                        newState[press] = !newState[press]
                    }
                    if(newState.toString() !in visited){
                        visited.add(newState.toString())
                        queue.add(Pair(newState, depth+1))
                    }
                }
            }
        }

        return ans
    }
    
    fun part2(input: List<String>): Int {
        return input.size
    }
    
    val testInput = readInput("Day10_test")
    check(part2(testInput) == 33)
    
    
    val input = readInput("Day10")
    part1(input).println()
    part2(input).println()
}
