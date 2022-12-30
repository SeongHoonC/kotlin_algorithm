package greedy.problem3

fun reverse(numbers: List<Int>): Int {
    var count = 0
    var countable = true
    val primary = numbers[0]
    repeat(numbers.size) {
        if (numbers[it] != primary && countable) {
            count++
            countable = false
        }
        if (numbers[it] == primary) {
            countable = true
        }
    }
    return count
}

fun main() {
    val input = readLine()
    val numbers = input?.chunked(1)?.map { it.toInt() }
    if (numbers != null) {
        println(reverse(numbers))
    }
}