package greedy.problem2

import java.lang.Integer.max

fun maxNum(numbers:List<Int>):Int{
    var maxNum = 0
    repeat(numbers.size){
        maxNum = max(maxNum + numbers[it],maxNum * numbers[it])
    }
    return maxNum
}

fun main() {
    val input = readLine()
    val numbers = input?.chunked(1)?.map { it.toInt() }
    if (numbers != null) {
        println(maxNum(numbers))
    }
}