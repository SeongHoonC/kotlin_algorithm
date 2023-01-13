package sort.problem24

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    var house = br.readLine().split(" ").map{it.toInt()}

    house = house.sortedBy { it }

    println(house[(n-1)/2])
}