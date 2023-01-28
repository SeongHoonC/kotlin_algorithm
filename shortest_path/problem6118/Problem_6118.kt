package shortest_path.problem6118

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val nm = br.readLine().split(" ").map { it.toInt() }
    val n = nm[0]
    val m = nm[1]
    val graph = List(n + 1) { mutableListOf<Int>() }

    for (i in 0 until m) {
        val input = br.readLine().split(" ").map { it.toInt() }
        graph[input[0]].add(input[1])
        graph[input[1]].add(input[0])
    }

    val distance = MutableList(n + 1) { Int.MAX_VALUE }

    dijkstra(1, graph, distance)

    var count = 0
    var maxIndex = 1

    //distance 돌면서 최장찾기
    for (i in 1 until n + 1) {
        if (distance[maxIndex] < distance[i]) {
            maxIndex = i
            count = 1
            continue
        }
        // 같으면 개수만 크게
        if (distance[maxIndex] == distance[i]){
            count++
        }
    }
    println("$maxIndex ${distance[maxIndex]} $count")
}

//우선순위 큐를 사용한 다익스트라
fun dijkstra(start: Int, graph: List<MutableList<Int>>, distance: MutableList<Int>) {

    //우선순위 큐에 저장 비용, 노드 순으로 저장
    val q = PriorityQueue<Pair<Int, Int>>() { p1, p2 -> // 우선
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
            val cost = dist + 1
            if (cost < distance[target]) {
                distance[target] = cost
                q.add(Pair(cost, target))
            }
        }
    }
}