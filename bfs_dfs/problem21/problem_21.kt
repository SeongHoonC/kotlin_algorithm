package bfs_dfs.problem21

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

val dx = listOf(-1, 1, 0, 0)
val dy = listOf(0, 0, -1, 1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val nlr = br.readLine().split(" ").map { it.toInt() }
    val n = nlr[0]
    val l = nlr[1]
    val r = nlr[2]
    val graph = mutableListOf<MutableList<Int>>()

    repeat(n) {
        val row = br.readLine().split(" ").map { it.toInt() }
        graph.add(row.toMutableList())
    }
    var day = 0

    while (true) {
        val visited = Array(n) { Array(n) { false } }
        var isUnion = false
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (bfs(graph, i, j, visited, l, r)) {
                    isUnion = true
                }
            }
        }
        if (!isUnion) {
            break
        }
        day++
    }
    println(day)
}


fun bfs(graph: List<MutableList<Int>>, i: Int, j: Int, visited: Array<Array<Boolean>>, l: Int, r: Int): Boolean {

    if (visited[i][j]) {
        return true
    }

    val n = graph.size
    val union = mutableListOf<Pair<Int, Int>>()

    val deque = ArrayDeque<Pair<Int, Int>>()

    union.add(Pair(i, j))
    deque.add(Pair(i, j))

    visited[i][j] = true
    var sum = graph[i][j]

    while (deque.isNotEmpty()) {
        val now = deque.removeFirst()
        val x = now.first
        val y = now.second

        for (dir in 0..3) {
            val nx = x + dx[dir]
            val ny = y + dy[dir]

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                continue
            }

            val diff = abs(graph[nx][ny] - graph[x][y])
            if (diff in l..r && !visited[nx][ny]) {
                visited[nx][ny] = true
                sum += graph[nx][ny]
                union.add(Pair(nx, ny))
                deque.add(Pair(nx, ny))
            }
        }
    }
    if (union.size == 1) {
        return false
    }
    val result = sum / union.size
    for (index in union.indices) {
        graph[union[index].first][union[index].second] = result
    }
    return true
}