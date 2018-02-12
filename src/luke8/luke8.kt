@file:Suppress("LoopToCallChain")

package luke8

import kotlinx.coroutines.experimental.async

import kotlinx.coroutines.experimental.runBlocking
import java.lang.Long.min
import kotlin.system.measureTimeMillis

lateinit var squares: Array<Int>
lateinit var juletallTable: BooleanArray

fun main(args: Array<String>) {
    println("Kjøretid : " + measureTimeMillis {
        squares = (0 .. 9)
                .map { it * it }
                .toTypedArray()

        juletallTable = (0 .. (9 * 9 * 10))
                .map { it != 0 && isJuletall(it) }
                .toBooleanArray()

        println("Summen er ${sumJuleTall(10_000_000, 1_250_000)}")
    } + "ms")
}

fun sumJuleTall(input: Long, blockSize: Int): Long {
    val jobs = (1 .. input).step(blockSize.toLong()).map {
        async {
            var tempSum: Long = 0
            for (n in it until min(it + blockSize, input + 1)) {
                if (juletallTable[sumDigits(n.toInt())]) {
                    tempSum += n
                }
            }
            tempSum
        }
    }
    return runBlocking {
        jobs.map { it.await() }.sum()
    }
}

fun isJuletall(num: Int): Boolean {
    var temp = num
    while (temp != 89) {
        temp = sumDigits(temp)
        if (temp == 1) {
            return true
        }
    }
    return false
}

fun sumDigits(n: Int): Int {
    var temp = n
    var sum = 0
    while (temp > 0){
        val i = temp % 10
        sum += squares[i]
        temp /= 10
    }
    return sum
}