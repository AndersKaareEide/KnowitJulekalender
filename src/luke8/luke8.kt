package luke8

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import java.lang.Long.min
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    println("Kjøretid : " + measureTimeMillis {
        println("Summen er ${sumJuleTall(10_000_000, 100_000)}")
    } + "ms")
}

fun sumJuleTall(input: Long, blockSize: Int): Long {
    val jobs = (1 .. input).step(blockSize.toLong()).map {
        async {
            var tempSum: Long = 0
            for (n in it until min(it + blockSize, input + 1)) {
                if (isJuletall(n.toInt())) {
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