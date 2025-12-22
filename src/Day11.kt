fun main() {
    fun part1(input: List<String>): Int {
        val connections = mutableMapOf<String, Set<String>>()
        for(line in input){
            val name_val = line.split(":")
            val values = name_val[1].trim().split(" ").toSet()
            connections[name_val[0]] = values
        }
        var answer = 0
        fun dfs(visited: MutableSet<String>, currentNode: String){
            if(currentNode == "out"){
                answer += 1
                return
            }
            if(currentNode in visited) {
                return
            }
            visited.add(currentNode)
            for(con in connections.getOrDefault(currentNode, mutableSetOf())){
                dfs(visited, con)
            }
            visited.remove(currentNode)
        }

        dfs(mutableSetOf<String>(), "you")
        return answer
    }
    
    fun part2(input: List<String>): Long {
        val connections = mutableMapOf<String, Set<String>>()
        for(line in input){
            val name_val = line.split(":")
            val values = name_val[1].trim().split(" ").toSet()
            connections[name_val[0]] = values
        }
        val cache = mutableMapOf<Triple<String, Boolean, Boolean>, Long>()

        fun dfs(currentNode: String, dac: Boolean, fft: Boolean): Long{
            val key = Triple(currentNode, dac, fft)
            cache[key]?.let{ return it}
            if(currentNode == "out"){
                if(dac && fft){
                    return 1L
                }
                else{
                    return 0L
                }
            }
            val newDac = dac || currentNode == "dac"
            val newFft = fft || currentNode == "fft"

            val res = connections[currentNode]?.sumOf { dfs(it, newDac, newFft) } ?: 0L
            cache[key] = res
            return res
        }

        val answer = dfs( "svr", false, false)
        return answer
    }
    
    val testInput = readInput("Day11_test")
    check(part2(testInput) == 2L)
    
    
    val input = readInput("Day11")
    part1(input).println()
    part2(input).println()
}
