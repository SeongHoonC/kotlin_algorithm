package greedy.makeLarge2812


fun removeNumbers(n: Int,k:Int, numbers: List<Int>): String {
    var numbers = numbers.toMutableList()
    var maxNumbers = mutableListOf<Int>()
    var preNum = 0
    for(i in k+1 .. n) {
        val subNumbers = numbers.subList(preNum, i)
        val maxIdx = (preNum + subNumbers.indices.maxBy { subNumbers[it] })
        preNum = maxIdx + 1
        maxNumbers.add(numbers[maxIdx])
    }
   return maxNumbers.joinToString("")
}

fun main() {
    val input1 = readLine()
    val nm = input1?.split(" ")?.map{it.toInt()}
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