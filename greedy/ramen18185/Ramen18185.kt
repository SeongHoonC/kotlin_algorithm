package greedy.ramen18185

import kotlin.math.min

fun buyRamen(n: Int, a: List<Int>): Int {
    var factories = a.toMutableList()
    var money = 0

    for(i in 0 until n-2){

        if(factories[i+1] > factories[i+2]){
           val minExceptNum = min(factories[i],factories[i+1]-factories[i+2])
            money += minExceptNum * 5
            for(j in 0 .. 1)
                factories[i+j] = factories[i+j] - minExceptNum
        }


        val minNum1 = minOf(factories[i], factories[i + 1], factories[i + 2])
        money += minNum1 * 7
        for (j in 0..2)
            factories[i + j] = factories[i + j] - minNum1

        val minNum2 = minOf(factories[i], factories[i + 1])
        money += minNum2 * 5
        for (j in 0..1)
            factories[i + j] = factories[i + j] - minNum2

        money += factories[i] * 3
    }

    val minNum3 = min(factories[n-2],factories[n-1])
    money += minNum3 * 5
    money += (factories[n-2] + factories[n-1] - minNum3 * 2) * 3
    return money
}

fun main() {
    val n = readLine()?.toInt()
    val input = readLine()
    val a = input?.split(" ")?.map { it.toInt() }

    if (n == null || a == null) {
        return
    }
    val money = buyRamen(n, a)
    println(money)
}