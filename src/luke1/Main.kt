package luke1

import java.nio.file.Paths
import java.util.*

fun main(args: Array<String>) {
    checkWords("aeteesasrsssstaesersrrsse")
}

fun checkWords(ngramAnagram: String) {
    Scanner(Paths.get("src/luke1/wordlist.txt")).forEach {
        val result = checkWord(ngramAnagram, it)
        if (result.first){
            println("The word is $it, n=${result.second}")
            return
        }
    }
}

fun checkWord(ngramAnagram: String, actualWord: String): Pair<Boolean, Int> {
    for (nValue in 2 .. 8) {
        val wordNgram = generateNgramString(actualWord, nValue)
        if (isPermutation(wordNgram, ngramAnagram)) {
            return Pair(true, nValue)
        }
    }
    return Pair(false, 0)
}

fun generateNgramString(input: String, n: Int): String {
    val result = StringBuilder()
    for (index in 0 .. input.length - n){
        result.append(input.slice(index until index + n))
    }
    return result.toString()
}

fun isPermutation(input: String, other: String): Boolean {
    if (input.length != other.length)
        return false

    val inputCharCount = getCharCountMap(input)
    val otherCharCount = getCharCountMap(other)

    return try {
        inputCharCount.keys.all { inputCharCount[it]!! == otherCharCount[it]!! }
    } catch (e: Exception){ false }
}

private fun getCharCountMap(input: String): MutableMap<Char, Int> {
    val charCountMap: MutableMap<Char, Int> = input.associate { it to 0 }.toMutableMap()
    input.forEach { charCountMap.put(it, charCountMap[it]!! + 1) }
    return charCountMap
}
