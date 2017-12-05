package luke3

import java.nio.file.Paths

fun main(args: Array<String>) {
    val imgPath = Paths.get("src/luke3/image.png")
    val imageByteArray = imgPath.toAbsolutePath().toFile().readBytes()
    val filteredArray = imageByteArray
            .map { it.toInt() }
            .filter {
                it == 32 || it in 48..57 || it in 65..90 || it in 97..122
            }.map { it.toChar() }
            .joinToString(separator = "")

    val stringSlices = mutableListOf<String>()
    for (index in 0..filteredArray.lastIndex - 50 step 50) {
        stringSlices.add(filteredArray.slice(index until index + 50))
    }
    stringSlices.add(filteredArray.slice(filteredArray.lastIndex - 50..filteredArray.lastIndex))

    println(stringSlices.joinToString(separator = "\n"))
}