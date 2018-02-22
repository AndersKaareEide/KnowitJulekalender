package luke10

fun main(args: Array<String>) {
    println(fastWinner(1500))
}

fun fastWinner(numPeople: Int): Int {
    return 2 * (numPeople - Integer.highestOneBit(numPeople)) + 1
}
