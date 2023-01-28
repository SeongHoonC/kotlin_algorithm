package shortest_path.scale10159

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()

    val graph = List(n + 1) { MutableList(n + 1) { false } }

    for (a in 1..n) {
        for (b in 1..n) {
            if (a == b) {
                graph[a][b] = true
            }
        }
    }

    for (i in 0 until m) {
        val input = br.readLine().split(" ").map { it.toInt() }
        graph[input[0]][input[1]] = true
    }

    for (k in 1..n) {
        for (a in 1..n) {
            for (b in 1..n) {
                if (graph[a][b]) {
                    continue
                }
                if (graph[a][k] && graph[k][b]) {
                    graph[a][b] = true
                }
            }
        }
    }

    for (i in 1..n) {
        var count = 0
        for (j in 1..n) {
            if (i == j) {
                continue
            }

            if (!graph[i][j] && !graph[j][i]) {
                count++
            }
        }
        println(count)
    }
}


