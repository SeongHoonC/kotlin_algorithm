package implement.problem13

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val nm = br.readLine().split(" ").map { it.toInt() }
    val n = nm[0]
    val m = nm[1]
    val chickens = mutableListOf<Pair<Int, Int>>()
    val houses = mutableListOf<Pair<Int, Int>>()

    for (i in 1..n) {
        val row = br.readLine().split(" ").map { it.toInt() }
        for (j in 1..n) {
            if (row[j - 1] == 2) {
                chickens.add(Pair(i, j))
            }
            if (row[j - 1] == 1) {
                houses.add(Pair(i, j))
            }
        }
    }

    val combinationList = mutableListOf<List<Int>>()
    val chickenList = List(chickens.size) { it }
    combination(combinationList, chickenList, Array(chickenList.size) { false }, 0, m)

    var minDistanceSum = 100_000_000
    repeat(combinationList.size) {combi->
        var selectedChickens = mutableListOf<Pair<Int,Int>>()
        repeat(m) {index->
            val value = combinationList[combi][index]
            selectedChickens.add(chickens[value])
        }

        var distanceSum = 0
        repeat(houses.size) { i ->
            var minDistance = 100
            repeat(selectedChickens.size) { j ->
                val distance = abs(houses[i].first - selectedChickens[j].first) + abs(houses[i].second - selectedChickens[j].second)
                minDistance = min(distance, minDistance)
            }
            distanceSum += minDistance
        }
        minDistanceSum = min(minDistanceSum,distanceSum)
    }
    println(minDistanceSum)
}

fun <T> combination(answer: MutableList<List<T>>, el: List<T>, ck: Array<Boolean>, start: Int, target: Int) {
    if (target == 0) {
        answer.addAll(listOf(el.filterIndexed { index, t -> ck[index] }))
    } else {
        for (i in start until el.size) {
            ck[i] = true
            combination(answer, el, ck, i + 1, target - 1)
            ck[i] = false
        }
    }
}