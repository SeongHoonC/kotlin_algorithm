package stack.baekjoon6198

import java.util.*

fun garden(n: Int): Long {
    val building = Stack<Int>()
    var count = 0L
    for(i in 0 until n){
        val height = readLine()?.toInt()
        while(true) {
            if(building.size == 0){
                building.push(height)
                break
            }
            if(building.peek()> height!!){
                count+= building.size
                building.push(height)
                break
            }
            building.pop()
        }
    }
    return count
}

fun main() {
    val n = readLine()?.toInt()
    val count = n?.let { garden(it) }
    println(count)
}