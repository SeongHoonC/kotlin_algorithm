package greedy.makeLarge2812

import java.util.*


fun removeNumbers(n: Int, k: Int, numbers: List<Int>): String {
    val len = n-k
    var k = k
    var maxNumbers = Stack<Int>()
    for (i in 0 until n) {
        while (true) {
            if (maxNumbers.size == 0) {
                maxNumbers.push(numbers[i])
                break
            }
            if (maxNumbers.peek() >= numbers[i]) {
                if(maxNumbers.size < len) {
                    maxNumbers.push(numbers[i])
                }
                break
            }
            if(k<=0) {
                maxNumbers.push(numbers[i])
                break
            }
            maxNumbers.pop()
            k--
        }
    }
    return maxNumbers.joinToString("")
}


fun main() {
    val input1 = readLine()
    val nm = input1?.split(" ")?.map { it.toInt() }
    val n = nm?.get(0)
    val k = nm?.get(1)
    val input2 = readLine()
    val numbers = input2?.chunked(1)?.map { it.toInt() }

    if (n == null || k == null || numbers == null) {
        return
    }
    val maxNum = removeNumbers(n, k, numbers)
    println(maxNum)
}