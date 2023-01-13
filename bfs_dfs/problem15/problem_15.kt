package bfs_dfs.problem15

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val nmkx = br.readLine().split(" ").map { it.toInt() }
    val n = nmkx[0]
    val m = nmkx[1]
    val k = nmkx[2]
    val x = nmkx[3]

    val deque = ArrayDeque<Int>()
    deque.add(x)
    val visited = IntArray(n + 1) { 0 }
    visited[x] = 0
    val graph = List(n + 1) { mutableListOf<Int>() }

    for (i in 1..m) {
        val edge = br.readLine().split(" ").map { it.toInt() }
        graph[edge[0]].add(edge[1])
    }

    var count = 0
    while (deque.isNotEmpty()) {
        count++
        val v = deque.removeFirst()

        for (i in 0 until graph[v].size) {
            if (visited[graph[v][i]] == 0 && graph[v][i] != x) {
                deque.add(graph[v][i])
                visited[graph[v][i]] = visited[v] + 1
            }
        }
    }
    if (k in visited) {
        for (i in visited.indices) {
            if (visited[i] == k) {
                println(i)
            }
        }
    } else{
        println(-1)
    }
}