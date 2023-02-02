package graph.problem7562

import java.io.BufferedReader
import java.io.InputStreamReader

val dx = intArrayOf(1, 1, -1, -1, 2, 2, -2, -2)
val dy = intArrayOf(-2, 2, -2, 2, -1, 1, -1, 1)
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()
    repeat(t) {
        println(movingKnight(br))
    }
}

fun movingKnight(br: BufferedReader):Int{
    val n = br.readLine().toInt()
    val (fromX, fromY) = br.readLine().split(" ").map { it.toInt() }
    val (toX, toY) = br.readLine().split(" ").map { it.toInt() }

    val deque = ArrayDeque<Pair<Int, Int>>()
    deque.add(Pair(fromX, fromY))
    val visited = Array(n + 1) { Array(n + 1) { 0 } }

    if( fromX == toX && fromY ==  toY ){
        return 0
    }

    while (deque.isNotEmpty()) {
        val (nowX, nowY) = deque.removeFirst()
        val cost = visited[nowX][nowY]
        for (i in 0 until 8) {
            val nx = nowX + dx[i]
            val ny = nowY + dy[i]

            if(nx == toX && ny == toY){
                return cost+1
            }

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                continue
            }
            if (visited[nx][ny] == 0) {
                visited[nx][ny] = cost + 1
                deque.add(Pair(nx, ny))
            }
        }
    }
    return 0
}
