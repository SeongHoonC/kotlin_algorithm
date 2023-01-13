package sort.problem25

fun main(){
    val n = 4
    val stages = intArrayOf(2,1,2,2)
    solution(n,stages)
}

fun solution(n: Int, stages: IntArray): IntArray {
    val fail = mutableListOf<Pair<Int,Double>>()
    val playing = MutableList(n+1){mutableListOf(0.0,0.0)}
    for(i in stages.indices){
        for(j in 1..n){
            if(stages[i] >= j )
                playing[j][0] +=1.0
            if(stages[i] == j)
                playing[j][1] +=1.0
        }
    }
    for(i in 1 .. n){
        if(playing[i][0] == 0.0){
            fail.add(Pair(i,0.0))
            continue
        }
        fail.add(Pair(i,playing[i][1]/playing[i][0]))
    }

    fail.sortWith(compareByDescending<Pair<Int, Double>> { it.second }.thenBy { it.first })

    val answer = IntArray(n){0}
    for(i in fail.indices){
        answer[i] = fail[i].first
    }
    println(fail)
    return answer
}
