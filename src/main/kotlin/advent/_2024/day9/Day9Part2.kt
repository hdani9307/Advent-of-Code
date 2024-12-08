package advent._2024.day9

import advent.helper.readInput
import advent.helper.runMeasured

class Day9Part2 {
    fun run(): Long {
        var sum = 0L
        val lines = readInput("2024/9.txt")
        val clusters = mutableListOf<MutableList<String>>()
        runMeasured {
            var index = 0
            for ((i, c) in lines.first().toList().withIndex()) {
                val l = mutableListOf<String>()
                for (j in 0 until c.toString().toInt()) {
                    if (i % 2 == 0) {
                        l.add(index.toString())
                    } else {
                        l.add(".")
                    }
                }
                if (l.isNotEmpty()) {
                    clusters.add(l)
                }
                if (i % 2 == 0) {
                    index++
                }
            }

            for (i in clusters.size - 1 downTo 0) {
                val rightestCluster = clusters[i]
                if (rightestCluster.contains(".")) {
                    continue
                }
                for (withIndex in clusters.withIndex()) {
                    if (withIndex.index > i) {
                        break
                    }
                    val freeSpace = withIndex.value.count { it == "." }
                    if (freeSpace >= rightestCluster.size) {
                        val tmp = mutableListOf<String>()
                        for (item in rightestCluster) {
                            val firstFreeSpaceIndex = withIndex.value.indexOf(".")
                            withIndex.value[firstFreeSpaceIndex] = item
                            tmp.add(".")
                        }
                        clusters[i] = tmp
                        break
                    }
                }
            }

            for (withIndex in clusters.flatten().withIndex()) {
                if (withIndex.value == ".") {
                    continue
                }
                sum += withIndex.value.toInt() * withIndex.index
            }

        }
        return sum
    }
}