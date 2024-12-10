package advent.helper

import java.io.File
import kotlin.time.Duration
import kotlin.time.measureTimedValue

fun readInput(fileName: String): List<String> {
    val absolutePath = File("").absolutePath
    return File("$absolutePath/src/test/resources/$fileName")
        .bufferedReader()
        .readLines()
}

fun List<String>.toMatrix(): List<List<Char>> {
    val matrix = mutableListOf<MutableList<Char>>()
    for ((i, line) in this.withIndex()) {
        val row = mutableListOf<Char>()
        for ((j, c) in line.withIndex()) {
            row.add(c)
        }
        matrix.add(row)
    }
    return matrix
}

fun <T> List<List<T>>.transpose(): List<List<T>> {
    return (this[0].indices).map { i -> (this.indices).map { j -> this[j][i] } }
}

fun <T> runMeasured(block: () -> T) {
    val (_: T, duration: Duration) = measureTimedValue {
        block()
    }

    println("${duration.inWholeMilliseconds} ms.")
}