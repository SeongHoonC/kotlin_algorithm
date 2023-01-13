package bfs_dfs.problem19

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min
import kotlin.math.max


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val arr = br.readLine().split(" ").map { it.toInt() }
    val input = br.readLine().split(" ").map { it.toInt() }
    val operator = mutableListOf<Int>()
    for (i in input.indices) {
        repeat(input[i]) {
            operator.add(i + 1)
        }
    }
    val permutationSet = permutation(operator).toSet()
    var maxNum = -1_000_000_000
    var minNum = 1_000_000_000

    permutationSet.forEach {
        var result = arr[0]
        for (index in 0 until n-1) {
            when(it[index]){
                1 -> result += arr[index+1]
                2 -> result -= arr[index+1]
                3 -> result *= arr[index+1]
                4 -> result /= arr[index+1]
            }
        }
        minNum = min(result, minNum)
        maxNum = max(result,maxNum)
    }
    println(maxNum)
    println(minNum)
}

fun <T> permutation(el: List<T>, fin: List<T> = listOf(), sub: List<T> = el): List<List<T>> {
    return if (sub.isEmpty()) listOf(fin)
    else sub.flatMap { permutation(el, fin + it, sub - it) }
}
