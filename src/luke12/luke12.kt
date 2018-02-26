package luke12

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val runTime = measureTimeMillis {
        val board: Array<Boolean> = Array(100) { true }
        val numMoves = 200
        var currentMove = Pos(0, 0)
        (1..numMoves).forEach { currentMove = makeNextMove(currentMove, board) }

        println("Antall svarte ruter etter $numMoves trekk: ${board.filter { !it }.count()}")
    }
    println("Kjøretid: ${runTime}ms")
}

fun makeNextMove(pos: Pos, board: Array<Boolean>): Pos {
    val standsOnWhite = board[pos.toInt()]
    board[pos.toInt()] = !board[pos.toInt()]

    val options = pos.getMoveList()
            .filter { it.isValid() }
            .map { it.toInt() }

    if (!options.any { board[it] == standsOnWhite }) {
        return Pos(options.max()!!)
    }

    return Pos(options.minBy {
        when (board[it] == standsOnWhite) {
            true -> it
            false -> Int.MAX_VALUE
        }
    }!!)
}


data class Pos(val y: Int, val x: Int) {

    constructor(num: Int) : this(num / 10, num % 10)

    fun isValid() = x in 0..9 && y in 0..9

    fun toInt() = y * 10 + x

    fun getMoveList() =
            listOf(Pos(y - 2, x - 1), Pos(y - 2, x + 1), Pos(y - 1, x - 2), Pos(y - 1, x + 2),
                    Pos(y + 1, x - 2), Pos(y + 1, x + 2), Pos(y + 2, x - 1), Pos(y + 2, x + 1))
}

