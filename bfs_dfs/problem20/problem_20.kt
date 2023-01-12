package bfs_dfs.problem20

import java.io.BufferedReader
import java.io.InputStreamReader

val dx = listOf(-1,1,0,0)
val dy = listOf(0,0,-1,1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val teachers = mutableListOf<Pair<Int,Int>>()
    val candidates = mutableListOf<Pair<Int,Int>>()

    val graph = MutableList(n){MutableList(n){"X"}}
    for(x in 0 until n){
        val input = br.readLine().split(" ")
        for(y in input.indices){
            if(input[y]=="T"){
                teachers.add(Pair(x,y))
            }
            if(input[y]=="X"){
                candidates.add(Pair(x,y))
            }
            graph[x][y] = input[y]
        }
    }

    val combinationList = mutableListOf<List<Int>>()
    val candidateList = List(candidates.size) { it }
    combination(combinationList, candidateList, Array(candidates.size) { false }, 0, 3)

    for (index in 0 until combinationList.size) {
        val objList = combinationList[index]
        for(i in 0..2) {
            val cx = candidates[objList[i]].first
            val cy = candidates[objList[i]].second
            graph[cx][cy] = "O"
        }

        if(check(graph,teachers,n)){
            println("YES")
            return
        }

        for(i in 0..2) {
            val cx = candidates[objList[i]].first
            val cy = candidates[objList[i]].second
            graph[cx][cy] = "X"
        }
    }
    println("NO")
}

fun <T> combination(answer: MutableList<List<T>>, el: List<T>, ck: Array<Boolean>, start: Int, target: Int) {
    if (target == 0) {
        answer.addAll(listOf(el.filterIndexed { index, t -> ck[index] }))
    } else {
        for (i in start until el.size) {
            ck[i] = true
            combination(answer, el, ck, i + 1, target - 1)
            ck[i] = false
        }
    }
}

fun check(graph:MutableList<MutableList<String>>,teachers:MutableList<Pair<Int,Int>>,n:Int):Boolean{
    teachers.forEach {teacher ->
        val tx = teacher.first
        val ty = teacher.second

        for(index in 0..3) {
            val directionX = dx[index]
            val directionY = dy[index]
            var nx = tx + directionX
            var ny = ty + directionY
            while (true) {
                if( nx < 0 || ny < 0 || nx >= n || ny >= n){
                    break
                }
                if( graph[nx][ny] == "O" ){
                    break
                }
                if(graph[nx][ny] =="S"){
                    return false
                }
                nx += directionX
                ny += directionY
            }
        }
    }
    return true
}