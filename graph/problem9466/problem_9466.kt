package graph.problem9466

import java.io.BufferedReader
import java.io.InputStreamReader

private lateinit var parents: IntArray
private lateinit var visited: Array<Boolean>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()
    repeat(t) {
        practice(br)
    }
}

fun dfs(x: Int, cycle:MutableList<Int>):Int{
    var x = x
    visited[x] = true
    cycle.add(x)
    while(true){
        if (visited[parents[x]]){
            if(cycle.contains(parents[x]))
                return cycle.size - cycle.indexOf(parents[x])
            return 0
        } else {
            x = parents[x]
            cycle.add(x)
            visited[x] = true
        }
    }
}

fun practice(br: BufferedReader) {
    val n = br.readLine().toInt()
    parents = IntArray(n + 1) { 0 }
    val input = br.readLine().split(" ").map { it.toInt() }
    for (i in 1..n) {
        parents[i] = input[i - 1]
    }
    visited = Array(n+1){false}

    var result = 0
    for (i in 1 .. n){
        if (!visited[i]){
            val cycle = mutableListOf<Int>()
            result += dfs(i,cycle)
        }
    }
    println(n - result)
}

