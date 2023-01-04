package greedy.problem4

fun impossible(n: Int, moneys: List<Int>): Int {
    val sortedMoneys = moneys.sorted()
    var minNum = 1
    for (i in 0 until n) {
        if (minNum >= sortedMoneys[i])
            minNum += sortedMoneys[i]
        else break
    }
    return minNum
}

fun main() {
    val n = readLine()?.toInt()
    val input = readLine()
    val moneys = input?.split(" ")?.map { it.toInt() }

    moneys?.let {
        if (n != null) {
            val minNum = impossible(n, it)
            println(minNum)
        }
    }
}