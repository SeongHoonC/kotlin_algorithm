package binary_search.solution2467

import kotlin.math.abs
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val solutions = br.readLine().split(" ").map { it.toInt() }
    mix(solutions.sortedWith(compareBy{it}))
}

fun mix(solutions: List<Int>) {
    var minIndex = 0
    var maxIndex = solutions.size - 1
    var result = 2_000_000_000
    var resultPair = Pair(0,0)
    while (minIndex < maxIndex) {
        val one = solutions[minIndex]
        val two = solutions[maxIndex]
        if (one > 0)
            if (result > one + solutions[minIndex + 1]) {
                resultPair = Pair(one,solutions[minIndex+1])
                break
            }
        if (two < 0)
            if (result > abs(two + solutions[maxIndex - 1])) {
                resultPair = Pair(solutions[maxIndex -1],two)
                break
            }

        val sum = one + two
        if(sum == 0){
            resultPair = Pair(one, two)
            break
        }
        if( abs(sum) < result ){
            result = abs(sum)
            resultPair = Pair(one, two)
        }
        if (sum > 0){
            maxIndex--
        }
        else {
            minIndex++
        }
    }
    println("${resultPair.first} ${resultPair.second}")
}