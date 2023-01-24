package dp.problem2293

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val nk = br.readLine().split(" ").map { it.toInt() }
    val n = nk[0]
    val k = nk[1]
    val array = IntArray(k + 1) { 0 }
    val coins = mutableListOf<Int>()
    for (i in 0 until n) {
        val coin = br.readLine().toInt()
        coins.add(coin)
    }

    array[0] = 1

    for (i in 0 until n) {
        for (j in coins[i]..k) {
            array[j] = array[j] + array[j - coins[i]]
        }
    }

    println(array[k])
}