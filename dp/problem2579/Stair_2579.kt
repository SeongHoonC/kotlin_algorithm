package dp.problem2579

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val dp = IntArray(n + 1) {0}
    val stairs = IntArray(n + 1) {0}

    for (i in 1..n){
        stairs[i] = br.readLine().toInt()
    }

    if(n <= 2){
        println(stairs[n]+stairs[n-1])
        return
    }

    dp[1] = stairs[1]
    dp[2] = stairs[1]+stairs[2]

    for(i in 3.. n){
        dp[i] = stairs[i] + max(stairs[i-1] + dp[i-3], dp[i-2])
    }
    println(dp[n])
}