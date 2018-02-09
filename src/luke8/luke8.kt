package luke8

import java.util.HashMap
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    println("Kjøretid : " + measureTimeMillis {
        println("Summen er ${sumJuleTall(10_000_000)}")
    })
}

val squares = (0 .. 9).associate { it to it * it}


fun sumJuleTall(n: Int): Long {
    var sum: Long = 0

    val blackList = mutableSetOf(20, 42, 145, 89, 85, 58, 29, 37, 25, 50, 61, 65, 17, 16, 40, 34, 100, 53, 26)

    
    for (num in 1 .. n) {
        var temp = num
        val tempBlackList = mutableListOf<Int>()
        while ((temp == 1 || temp > 9) && temp !in blackList && temp !in tempBlackList) {
            tempBlackList.add(temp)
            temp = sumDigits(temp)
            if (temp == 1) {
                sum += num
                break
            }
        }
    }

    return sum
}

fun sumDigits(n: Int): Int {
    var temp = n
    var sum = 0
    while (temp > 0){
        val i = temp % 10
        sum += i * i
        temp /= 10
    }
    return sum
}

