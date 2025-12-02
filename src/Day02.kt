fun main() {
    fun part1(input: List<String>): Long {
        val line = input[0]
        val sequences = line.split(",")
        var total = 0L
        for(seq in sequences){
            val start = seq.split("-")[0].toLong()
            val end  = seq.split("-")[1].toLong()

            for (i in start..end){
                if (i.length() % 2 != 0){
                    continue
                }
                val numAsStr = i.toString()
                val half = (i.length()/2)
                val firstHalf = numAsStr.substring(0, half)
                val secondHalf = numAsStr.substring(half)
                if(firstHalf == secondHalf){
                    total += i
                }
            }

        }
        return total
    }

    fun isRepeating(num: Long): Boolean {
        val half = (num.length()/2)
        for (i in 1..half){
            val numAsStr = num.toString()
            if(num.length() % i != 0){
                continue
            }
            val repeatCount = num.length()/i
            val sb = StringBuilder()
            repeat(repeatCount) {
                sb.append(numAsStr.substring(0, i))
            }
            if(sb.toString() == numAsStr){
                return true
            }
        }

        return false
    }

    fun part2(input: List<String>): Long {
        val line = input[0]
        val sequences = line.split(",")
        var total = 0L
        for(seq in sequences){
            val start = seq.split("-")[0].toLong()
            val end  = seq.split("-")[1].toLong()

            for (i in start..end){
                if(isRepeating(i)){
                    total += i
                }
            }

        }
        return total
    }

    val testInput = readInput("Day02_test")
    check(part2(testInput) == 4174379265L)


    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
