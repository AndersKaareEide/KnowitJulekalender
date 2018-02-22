package luke11

import org.apache.commons.math3.primes.Primes
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    println(measureTimeMillis {
        println((1..1000).filter(Primes::isPrime)
                .filter { it != it.toString().reversed().toInt() }
                .map { it.toString().reversed().toInt() }
                .filter(Primes::isPrime)
                .count())
    }.toString() + "ms")
}