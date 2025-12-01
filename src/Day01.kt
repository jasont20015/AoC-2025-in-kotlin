fun main() {
    fun part1(input: List<String>): Int {
        var cur = 50
        var counter = 0
        for(line in input) {
            val direction = line[0]
            val num = line.drop(1).toInt()
            if (direction == 'L'){
                cur -= num
            }
            else if (direction == 'R'){
                cur += num
            }
            while(cur < 0){
                cur += 100
            }
            cur %= 100

            if(cur == 0){
                counter += 1
            }
        }
        return counter
    }

    fun part2(input: List<String>): Int {
        var cur = 50
        var counter = 0
        for(line in input) {
            val direction = line[0]
            val num = line.drop(1).toInt()
            for (i in 1..num){
                if(direction == 'L'){
                    cur--
                }
                else{
                    cur++
                }
                cur %= 100
                if (cur == 0){
                    counter += 1
                }
            }
        }
        return counter
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 6)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
