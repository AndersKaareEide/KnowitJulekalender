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

fun oldShit(maxNum: Int): List<Pair<Int, Int>> {
    return (1..maxNum).map { tall ->
        var numSequences = 0
        for (num in 1..tall / 2) {
            var sequenceSum = num
            var n = 1
            while (sequenceSum <= tall) {
                sequenceSum += num + n
                n++
                if (sequenceSum == tall)
                    numSequences++
            }
        }
        Pair(tall, numSequences)
    }
}

//1,2,3,4,5,6
//2,3,4
//3,4,5
//2,3
//2,3,4
//1,2,3,4
//3,4


//Alle oddetall kan summeres sammen som n/2 + (n/2 + 1)
