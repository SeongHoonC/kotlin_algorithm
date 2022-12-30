package greedy.problem1

fun guild(n: Int, fears: List<Int>): Int {
    val sortedFear = fears.sorted()
    var group = mutableListOf(sortedFear[0])
    var count = 0
    for (i in 1 until n) {
        if (group.size < group[0]) {
            group.add(sortedFear[i])
        } else {
            count++
            group = mutableListOf(sortedFear[i])
        }
    }
    return count
}

fun main() {
    val n = readLine()?.toInt()
    val input = readLine()
    val fears = input?.split(" ")?.map { it.toInt() }

    fears?.let {
        if (n != null) {
            val groupCount = guild(n, it)
            println(groupCount)
        }
    }
}