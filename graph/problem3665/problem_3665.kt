package graph.problem3665

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()
    repeat(t) {
        acm(br)
    }

}

fun acm(br: BufferedReader) {
    val n = br.readLine().toInt()
    val graph = List(n + 1) { mutableListOf<Int>() }
    val indegree = MutableList(n + 1) { 0 }
    val grade = mutableListOf(0)
    br.readLine().split(" ").map { grade.add(it.toInt()) }
    val m = br.readLine().toInt()
    val changed = mutableListOf<Pair<Int, Int>>()
    repeat(m) {
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        changed.add(Pair(a, b))
    }

    for (i in 1 until n) {
        for (j in i + 1..n) {
            graph[grade[j]].add(grade[i])
            indegree[grade[i]]++
        }
    }
    for (i in changed.indices) {
        val one = changed[i].first
        val two = changed[i].second
        if (one in graph[two]) {
            graph[two].remove(one)
            graph[one].add(two)
            indegree[one]--
            indegree[two]++
        } else {
            graph[one].remove(two)
            graph[two].add(one)
            indegree[one]++
            indegree[two]--
        }
    }


    val result = mutableListOf<Int>()

    val q = ArrayDeque<Int>()
    for (i in 1..n) {
        if (indegree[i] == 0) {
            q.add(i)
        }
    }

    while (q.isNotEmpty()) {
        val now = q.removeFirst()
        result.add(now)

        if (q.size > 1) {
            println("?")
            return
        }

        for (e in graph[now].indices) {
            val target = graph[now][e]
            indegree[target] -= 1
            if (indegree[target] == 0) {
                q.add(target)
            }
        }
    }

    if (result.size == n) {
        for (i in result.size - 1 downTo 0) {
            print("${result[i]} ")
        }
    } else {
        print("IMPOSSIBLE")
    }
    println("")
}