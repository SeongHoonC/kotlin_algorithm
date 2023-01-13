package implement.problem11

import java.io.BufferedReader
import java.io.InputStreamReader

//동 남 서 북
val direction = arrayOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val board = Array(n) { IntArray(n) { 0 } }
    val k = br.readLine().toInt()

    for (i in 0 until k) {
        val input = br.readLine()
        val rowColumn = input.split(" ").map { it.toInt() }
        board[rowColumn[0] - 1][rowColumn[1] - 1] = 1
    }

    val l = br.readLine().toInt()
    val turning = mutableMapOf<Int, String>()
    for (i in 0 until l) {
        val input = br.readLine()
        val timeDirection = input.split(" ")
        turning[timeDirection[0].toInt() + 1] = timeDirection[1]
    }

    //snake 덱 생성하고 시작 위치 0,0
    val snake = ArrayDeque<Pair<Int, Int>>()
    snake.add(Pair(0, 0))
    var directIndex = 0
    var count = 0

    //무한루프
    while (true) {
        count++
        val head = snake.first()

        //해당 초에 회전해야 하는지 판단
        if (!turning[count].isNullOrEmpty()) {
            if (turning[count] == "D")
                directIndex = (directIndex + 1) % 4
            if (turning[count] == "L")
                directIndex = if (directIndex == 0) {
                    3
                } else (directIndex - 1)
        }

        //회전 결과에 따라 방향 변경
        val nextMove = direction[directIndex]
        val target = Pair(head.first + nextMove.first, head.second + nextMove.second)

        //벽에 부딪히는지 체크
        if (target.first < 0 || target.second < 0 || target.first >= n || target.second >= n) {
            break
        }

        //뱀 몸에 부딪히는지 체크
        if (target in snake) {
            break
        }

        //타겟을 헤드에 추가
        snake.addFirst(target)

        //사과가 있으면 먹고 0으로 만들기 그렇지 않으면 꼬리 제거
        if (board[target.first][target.second] == 1) {
            board[target.first][target.second] = 0
        } else {
            snake.removeLast()
        }
    }
    println(count)
}
