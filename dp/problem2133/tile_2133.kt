package dp.problem2133

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    if (n == 1 || n == 3) {
        println(0)
        return
    }

    if (n == 2) {
        println(3)
        return
    }

    val dp = IntArray(n + 1) { 0 }
    dp[0] = 1
    dp[2] = 3

    for (i in 4..n step (2)) {
        dp[i] = 4 * dp[i - 2] - dp[i - 4]
    }
    println(dp[n])
}