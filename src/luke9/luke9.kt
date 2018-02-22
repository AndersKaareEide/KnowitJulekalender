package luke9

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    var sum = 0
    val time = measureTimeMillis {
        val maxNum = 130_000
        val array = IntArray(maxNum + 1)

        for (number in 1..maxNum / 2) {
            val sequence = generateSequence(number) { it + 1 }.iterator()
            var sequenceSum = sequence.next() + sequence.next()
            while (sequenceSum <= maxNum) {
                array[sequenceSum]++
                sequenceSum += sequence.next()
            }
        }

        sum = array.sum()
    }

    println("Kjøretid: ${time}ms, sum: $sum")
}
