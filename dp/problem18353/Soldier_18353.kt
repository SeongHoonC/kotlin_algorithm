package dp.problem18353

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val soldiers = br.readLine().split(" ").map { it.toInt() }
    val dp = MutableList(n) { 1 }
    for (i in 1 until n) {
        for (j in 0 until i) {
            if (soldiers[j] > soldiers[i])
                dp[i] = max(dp[i], dp[j] + 1)
        }
    }
    println(n - dp.max())
}

