fun main() {

    val dx = intArrayOf(0, 1, 1, 1, 0, -1, -1, -1)
    val dy = intArrayOf(1, 1, 0, -1, -1, -1, 0, 1)

    fun checkAdjacent(grid: List<String>, x: Int, y: Int): Int{
        var adjacentElements = 0
        for(direction in 0..<8){
            val newX = x+dx[direction]
            val newY = y+dy[direction]
            if(0 <= newX && newX < grid[0].length && (0 <= newY && newY < grid.size) && grid[newY][newX] == '@'){
                adjacentElements++
            }

        }
        return adjacentElements
    }

    fun part1(input: List<String>): Int {
        var reachable = 0
        for(y in 0..<input.size){
            for(x in 0..<input[0].length){

                if(input[y][x] == '@' && checkAdjacent(input, x, y) < 4){
                    reachable++
                }
            }
        }

        return reachable
    }

    fun checkAdjacentGrid(grid: List<List<Char>>, x: Int, y: Int): Int{
        var adjacentElements = 0
        for(direction in 0..<8){
            val newX = x+dx[direction]
            val newY = y+dy[direction]
            if(0 <= newX && newX < grid[0].size && (0 <= newY && newY < grid.size) && grid[newY][newX] == '@'){
                adjacentElements++
            }

        }
        return adjacentElements
    }

    fun part2(input: List<String>): Int {
        var reachable = 0
        var reachableBefore = -1
        var grid = mutableListOf<MutableList<Char>>()

        for (y in 0..<input.size) {
            grid.add(mutableListOf())
            for (x in 0 ..<input[y].length) {
                grid[y].add(input[y][x])
            }
        }

        while (true) {
            if (reachableBefore == reachable){
                break
            }
            reachableBefore = reachable
            for (y in 0..<grid.size) {
                for (x in 0..<grid[0].size) {
                    if (grid[y][x] == '@' && checkAdjacentGrid(grid, x, y) < 4) {
                        reachable++
                        grid[y][x] = '.'
                    }
                }
            }
        }

        return reachable
    }
    
    val testInput = readInput("Day04_test")
    check(part2(testInput) == 43)
    
    
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
