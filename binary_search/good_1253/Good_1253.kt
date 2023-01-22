package binary_search.good_1253

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    var numbers = br.readLine().split(" ").map { it.toInt() }
    numbers = numbers.sortedWith(compareBy { it })

    var result = 0
    for (i in numbers.indices) {
        val value = numbers[i]
        var left = 0
        var right = n - 1
        while (left < right) {
            if(left == i) {
                left++
                continue
            }
            if(right == i){
                right--
                continue
            }
            val sum = numbers[left] + numbers[right]
            if (sum == value) {
                result++
                break
            }
            else if (sum < value) {
                left++
            }else {
                right--
            }
        }
    }
    println(result)
}
