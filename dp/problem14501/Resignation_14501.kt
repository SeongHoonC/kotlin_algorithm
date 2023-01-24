package dp.problem14501

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val schedule = IntArray(n + 2) { 0 }
    val dayList = mutableListOf(Pair(0, 0))

    for (i in 1..n) {
        val input = br.readLine().split(" ").map { it.toInt() }
        dayList.add(Pair(input[0], input[1]))
    }
    dayList.add(Pair(0, 0))

    for (i in 1..n + 1) {
        schedule[i] = max(schedule[i - 1], schedule[i])
        val target = i + (dayList[i].first)
        if (target > n + 1) {
            continue
        }
        val value = schedule[i] + dayList[i].second
        schedule[target] = max(value, schedule[target])
    }

    println(schedule[n + 1])
}