package luke4

import java.io.File
import java.io.FileReader

fun main(args: Array<String>) {
    val wordList = FileReader(File("src/luke4/anagramlist.txt")).readLines()
    val filtered = wordList.filter { !isPalindrome(it) }
            .filter { hasPalindromicAnagram(it) }

    println("${filtered.size} words are not palindromes, but have anagrams which are")
}

fun isPalindrome(word: String): Boolean {
    var frontIndex = 0
    var rearIndex = word.lastIndex

    while (frontIndex < rearIndex){
        if (word[frontIndex] != word[rearIndex])
            return false
        frontIndex++
        rearIndex--
    }
    return true
}

fun hasPalindromicAnagram(word: String): Boolean {
    val charCounts = getCharCountMap(word)
    return charCounts.filter { charCounts[it.key]!! % 2 == 1 }.size < 2
}

private fun getCharCountMap(input: String): MutableMap<Char, Int> {
    val charCountMap: MutableMap<Char, Int> = input.associate { it to 0 }.toMutableMap()
    input.forEach { charCountMap.put(it, charCountMap[it]!! + 1) }
    return charCountMap
}
