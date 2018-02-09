package luke6

import java.io.File
import java.io.FileReader
import java.lang.Math.*

val oslo = Capital("Oslo", 59.911491, 10.757933)

fun main(args: Array<String>) {
    val reader = FileReader(File("src/luke6/verda.txt"))

    val capitalsByDistance = reader.readLines()
            .map { it.split("\t") }
            .filter { it[6] == "Hovedstad" }
            .toSet().toList()
            .map { Capital(it[2], it[12].toDouble(), it[13].toDouble()) }
            .map { getDistanceToOslo(it) * 2 } //Double distance since we fly back and forth
            .sorted()

    var maxDistance = 7_274.0 * 24
    var numVisited = 0

    for ((index, capital) in capitalsByDistance.withIndex()) {
        if (maxDistance - capitalsByDistance[index] < 0)
            break
        maxDistance -= capitalsByDistance[index]
        numVisited++
    }

    println("Number of visited capitals: $numVisited")
}

data class Capital(val name: String, val lat: Double, val long: Double)

fun getDistanceToOslo(capital: Capital): Double {
    val radius = 6371.0
    val dLat = toRadians(capital.lat - oslo.lat)
    val dLng = toRadians(capital.long - oslo.long)

    val startLat = toRadians(oslo.lat)
    val endLat = toRadians(capital.lat)

    val a = square(sin(dLat / 2)) + cos(startLat) * cos(endLat) * square(sin(dLng / 2))

    return 2 * radius * asin(sqrt(a))
}

fun deg2rad(deg: Double) = deg * PI / 180


fun square(x: Double) = pow(x, 2.0)
