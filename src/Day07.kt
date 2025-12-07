fun main() {
    fun part1(input: List<String>): Int {
        val indexes = mutableSetOf<Int>()
        var splits = 0
        outer@ for(line in input){
            if(indexes.size == 0) {
                for (c in 0..<line.length) {
                    if(line[c] == 'S'){
                        indexes.add(c)
                        continue@outer
                    }
                }
            }
            val toRemove = mutableSetOf<Int>()
            val toAdd = mutableSetOf<Int>()

            for (index in indexes){
                if(line[index] == '^'){
                    toRemove.add(index)
                    toAdd.add(index + 1)
                    toAdd.add(index - 1)
                    splits += 1
                }
            }
            indexes.removeAll(toRemove)
            indexes.addAll(toAdd)
        }

        return splits
    }
    
    fun part2(input: List<String>): Long {
        var start = -1
        for (c in 0..<input[0].length){
            if(input[0][c] == 'S'){
                start = c
                break
            }
        }
        val cache = mutableMapOf<Pair<Int, Int>, Long>()
        fun dp(r:Int, c:Int): Long{
            if(c < 0 || c > input[0].length){
                return 0
            }
            if(r == input.size){
                return 1
            }
            if(cache.containsKey(Pair(r,c))){
                return cache[Pair(r,c)]!!
            }
            var result = -1L
            if (input[r][c] == '^'){
                result = dp(r+1, c-1) + dp(r+1, c+1)
            }
            else{
                result = dp(r+1, c)
            }
            cache[Pair(r,c)] = result
            return result

        }
        return dp(1,start)
    }
    
    val testInput = readInput("Day07_test")
    check(part2(testInput) == 40L)
    
    
    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}
