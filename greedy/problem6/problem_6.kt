package greedy.problem6

fun solution(food_times: IntArray, k: Long): Int {
    var k = k
    var foods = mutableListOf<Pair<Int, Int>>()
    val n = food_times.size
    for (i in 0 until n) {
        foods.add(Pair(food_times[i], i + 1))
    }
    foods.sortBy { it.first }

    var preTime = 0
    for (i in 0 until n) {
        val diff = foods[i].first - preTime
        if (diff != 0) {
            val spend = diff * (n - i)
            if (spend <= k){
                k -= spend
                preTime = foods[i].first
            }
            else {
                k %= (n - i)
                val subFoods = foods.subList(i,n)
                subFoods.sortBy { it.second }
                return subFoods[k.toInt()].second
            }
        }
    }
    return -1
}

fun main() {
    val food_times = intArrayOf(3, 5, 1, 6, 5, 3)
    val k = 20L
    val count = solution(food_times, k)
    println(count)
}