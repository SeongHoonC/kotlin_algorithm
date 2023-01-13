package bfs_dfs.problem16

import java.io.BufferedReader
import java.io.InputStreamReader

val dx = listOf(-1,1,0,0)
val dy = listOf(0,0,-1,1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val nm = br.readLine().split(" ").map { it.toInt() }
    val n = nm[0]
    val m = nm[1]
    val graph = mutableListOf<MutableList<Int>>()
    val virus = mutableListOf<Pair<Int, Int>>()
    val wall = mutableListOf<Pair<Int, Int>>()
    val candidate = mutableListOf<Pair<Int,Int>>()

    for(i in 0 until n){
        val inputRow = br.readLine().split(" ").map{it.toInt()}
        graph.add(inputRow.toMutableList())
        for(j in 0 until m) {
            if (inputRow[j] == 0)
                candidate.add(Pair(i,j))
            if (inputRow[j] == 2)
                virus.add(Pair(i,j))
        }
    }
    val combinationList = mutableListOf<List<Int>>()
    val candidateList = List(candidate.size) { it }
    combination(combinationList, candidateList, Array(candidate.size) { false }, 0, 3)
    var maxSafeZone = 0
    for(index in combinationList.indices){
        for(ex in 0..2){
            val x = candidate[combinationList[index][ex]].first
            val y = candidate[combinationList[index][ex]].second
            graph[x][y] = 1
        }

        var safeZone = candidate.size-3
        var visited =  MutableList(n){MutableList(m){false} }
        for(vi in virus.indices){
            val count = bfs(virus[vi].first,virus[vi].second,graph,n,m,visited)
            safeZone -= count
        }

        for(ex in 0..2){
            val x = candidate[combinationList[index][ex]].first
            val y = candidate[combinationList[index][ex]].second
            graph[x][y] = 0
        }

        if(safeZone > maxSafeZone){
            maxSafeZone = safeZone
        }
    }
    println(maxSafeZone)
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

fun bfs(x:Int, y:Int, graph:MutableList<MutableList<Int>>, n:Int, m:Int,visited:MutableList<MutableList<Boolean>>):Int{
    var count = 0
    var deque = ArrayDeque<Pair<Int,Int>>()
    deque.add(Pair(x,y))
    while(deque.isNotEmpty()){
        val xyPair = deque.removeFirst()
        val x = xyPair.first
        val y = xyPair.second
        for(i in 0..3){
            val nx = x + dx[i]
            val ny = y + dy[i]

            if(nx<0 || ny<0 || nx>= n || ny>=m){
                continue
            }
            if(graph[nx][ny] == 0 && !visited[nx][ny]){
                visited[nx][ny] = true
                count++
                deque.add(Pair(nx,ny))
            }
        }
    }
    return count
}
