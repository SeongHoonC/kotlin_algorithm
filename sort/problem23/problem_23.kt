package sort.problem23

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val classRoom = mutableListOf<Pair<String, List<Int>>>()

    repeat(n) {
        val person = br.readLine().split(" ")
        val pointList = listOf(person[1].toInt(), person[2].toInt(), person[3].toInt())
        classRoom.add(Pair(person[0], pointList))
    }

    classRoom.sortWith(compareByDescending<Pair<String, List<Int>>> { it.second[0] }.thenBy { it.second[1] }
        .thenByDescending { it.second[2] }.thenBy { it.first })

    repeat(n) {
        println(classRoom[it].first)
    }
}