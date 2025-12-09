plugins {
    kotlin("jvm") version "2.2.21"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

tasks {
    wrapper {
        gradleVersion = "9.2.1"
    }
}
dependencies {
    implementation(kotlin("stdlib"))
    // https://mvnrepository.com/artifact/org.locationtech.jts/jts-core
    implementation("org.locationtech.jts:jts-core:1.20.0")
}
repositories {
    mavenCentral()
}


task("generateNextDay") {
    doLast {
        val prevDayNum = fileTree("$projectDir/src").matching {
            include("Day*.kt")
        }.maxOf {
            val (prevDayNum) = Regex("Day(\\d\\d)").find(it.name)!!.destructured
            prevDayNum.toInt()
        }
        val newDayNum = String.format("%02d", prevDayNum + 1)
        File("$projectDir/src", "Day$newDayNum.kt").writeText(
            """fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }
    
    fun part2(input: List<String>): Int {
        return input.size
    }
    
    val testInput = readInput("Day${newDayNum}_test")
    check(part1(testInput) == 1)
    
    
    val input = readInput("Day${newDayNum}")
    part1(input).println()
    part2(input).println()
}
"""
        )
        File("$projectDir/resources", "Day${newDayNum}_test.txt").writeText("")
        File("$projectDir/resources", "Day${newDayNum}.txt").writeText("")
    }
}


