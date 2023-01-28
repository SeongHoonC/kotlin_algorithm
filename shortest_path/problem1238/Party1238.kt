package shortest_path.problem1238

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val nmx = br.readLine().split(" ").map { it.toInt() }
    val n = nmx[0]
    val m = nmx[1]
    val x = nmx[2]
    val graph = List(n + 1) { mutableListOf<Pair<Int,Int>>() }
    for (i in 0 until m) {
        val input = br.readLine().split(" ").map { it.toInt() }
        graph[input[0]].add(Pair(input[1],input[2]))
    }

    val distance = MutableList(n+1){MutableList(n + 1) { Int.MAX_VALUE }}
    for(i in 1..n) {
        dijkstra(i, graph, distance[i])
    }
    var maxTime = 0

    for(i in 1 until n+1){
        if(i == x) {
            continue
        }
        val sum = distance[i][x] + distance[x][i]
        maxTime = max(sum,maxTime)
    }

    println(maxTime)
}

fun dijkstra(start: Int, graph: List<MutableList<Pair<Int,Int>>>, distance: MutableList<Int>) {

    //우선순위 큐에 저장 비용, 노드 순으로 저장
    val q = PriorityQueue<Pair<Int, Int>>{ p1, p2 -> p1.first - p2.first // 우선 순위 큐
        when {
            p1.first > p2.first -> 1
            p1.first < p2.first -> -1
            else -> 0
        }
    }
    q.add(Pair(0, start))
    distance[start] = 0

    while (q.isNotEmpty()) {
        val information = q.poll()
        val dist = information.first
        val now = information.second

        if (distance[now] < dist) {
            continue
        }

        for (i in 0 until graph[now].size) {
            val target = graph[now][i]
            val time = dist + target.second
            if (time < distance[target.first]) {
                distance[target.first] = time
                q.add(Pair(time, target.first))
            }
        }
    }
}