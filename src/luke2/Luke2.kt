package luke2

fun main(args: Array<String>) {
    val maze = mutableListOf<List<Node>>()
    val mazeSize = 1000
    for (yCoord in 1..mazeSize) {
        val row = (1..mazeSize).map { createNode(it, yCoord) }
        maze.add(row)
    }

    val numOpenCells = maze.flatMap { it }.filter { !it.isWall }.size
    val numReachable = countReachableNodes(maze)
    println("Num open: $numOpenCells, num inaccessible: ${numOpenCells - numReachable}")
//    printMaze(maze)
}

private fun printMaze(maze: MutableList<List<Node>>) {
    maze.forEach {
        println(it.joinToString(separator = " ") {
            when {
                it.isWall -> "#"
                it.visited -> "O"
                else -> "X"
            }
        })
    }
}

fun createNode(xCoord: Int, yCoord: Int): Node {
    val result: Long = xCoord.toLong() * (12 * yCoord.toLong() + 5 * yCoord * yCoord + xCoord.toLong() * xCoord)
    if (result < 0)
        throw Exception()
    val binaryString = result.toString(2)
    val isWall = binaryString.filter { it == '1' }.length % 2 != 0

    return Node(isWall, false, Pair(xCoord - 1, yCoord - 1))
}

fun countReachableNodes(maze: List<List<Node>>): Int {
    var numReachable = 0
    val buffer = mutableListOf<Node>(maze[0][0])
    buffer.first().visited = true

    while (buffer.isNotEmpty()) {
        val currentNode = buffer.first()
        buffer.remove(currentNode)
        numReachable++

        exploreNode(maze, buffer, currentNode)
    }
    return numReachable
}

fun exploreNode(maze: List<List<Node>>, buffer: MutableList<Node>, node: Node) {
    val funcs = listOf(Node::up, Node::down, Node::left, Node::right)
    for (func in funcs) {
        try {
            val neighbor = maze[func(node).second][func(node).first]
            if (!neighbor.isWall && !neighbor.visited) {
                neighbor.visited = true
                buffer.add(neighbor)
            }
        } catch (e: Exception) {
        }
    }
}

data class Node(val isWall: Boolean, var visited: Boolean, private val coords: Pair<Int, Int>) {
    fun up(): Pair<Int, Int> = coords.copy(second = coords.second - 1)
    fun down(): Pair<Int, Int> = coords.copy(second = coords.second + 1)
    fun left(): Pair<Int, Int> = coords.copy(first = coords.first - 1)
    fun right(): Pair<Int, Int> = coords.copy(first = coords.first + 1)
}