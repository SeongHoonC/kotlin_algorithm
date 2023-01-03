package greedy.problem3

fun reverse(numbers: List<Int>): Int {
    var count = 0
    var countable = true
    // 첫번째 숫자를 기준으로 한다
    val primary = numbers[0]

    repeat(numbers.size) {
        //첫 번쨰 숫자와 다르면 뒤집기 한번 추가 countable false
        if (numbers[it] != primary && countable) {
            count++
            countable = false
        }
        //첫번째 숫자와 같아지면 다시 countable true
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