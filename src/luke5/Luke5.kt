package luke5

fun main(args: Array<String>) {
    var index = 0
    var num: Long = 1
    val list = mutableListOf<Long>()
    while (list.size < 1_000_000) {
        list.add(num)
        for (counter in list[index] downTo 2) {
            list.add(num)
        }
        index++
        num++
    }
    println("Sum: ${list.take(1_000_000).sum()}")
}