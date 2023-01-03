package greedy.problem1

fun guild(n: Int, fears: List<Int>): Int {
    //정렬
    val sortedFear = fears.sorted()

    //그룹 만들기
    var group = mutableListOf(sortedFear[0])
    var count = 0

    for (i in 1 until n) {

        //그룹 크기가 더 적으면 추가
        if (group.size < group.max()) {
            group.add(sortedFear[i])
        }

        //그렇지 않으면 그룹 개수 추가 및 초기화
        else {
            count++
            group = mutableListOf(sortedFear[i])
        }
    }

    //마지막 한 개 개수 확인
    if(group.size == group.max()){
        count ++
    }
    return count
}

fun main() {
    val n = readLine()?.toInt()
    val input = readLine()
    val fears = input?.split(" ")?.map { it.toInt() }

    fears?.let {
        if (n != null) {
            val groupCount = guild(n, it)
            println(groupCount)
        }
    }
}