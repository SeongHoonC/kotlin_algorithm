package greedy.problem5

fun pickBalls(n: Int,m:Int, balls: List<Int>): Int {
    var count = 0
    for(i in 0 until n-1){
        for(j in i until n){
            if(balls[i] != balls[j])
                count++
        }
    }
    return count
}

fun main() {
    val input1 = readLine()
    val nm = input1?.split(" ")?.map{it.toInt()}
    val n = nm?.get(0)
    val m = nm?.get(1)
    val input2 = readLine()
    val balls = input2?.split(" ")?.map { it.toInt() }

    if (n == null || m == null || balls == null) {
        return
    }
    val count = pickBalls(n, m, balls)
    println(count)
}