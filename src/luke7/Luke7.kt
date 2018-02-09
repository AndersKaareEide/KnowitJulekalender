package luke7

val ciphermapping = ('A' .. 'Z')
        .map { encrypt(it) to it }
        .toMap()

fun main(args: Array<String>) {
    println("JULEMANN".map { encrypt(it) }.joinToString(""))
    println("PWVAYOBB".map { decrypt(it) }.joinToString(""))
    println("OTUJNMQTYOQOVVNEOXQVAOXJEYA".map { decrypt(it) }.joinToString(""))
}

fun encrypt(input: Char): Char {
    val charPos = (input - 64).toInt()
    val cipher = (input + charPos).toInt()
    return ((((input + cipher) - 64).toInt() % 26) + 64).toChar()
}

fun decrypt(input: Char): Char {
    return ciphermapping[input]!!
}

private operator fun Char.plus(cipher: Char): Char = this + cipher.toInt()
