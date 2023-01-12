package bfs_dfs.problem17

import java.io.BufferedReader
import java.io.InputStreamReader

val dx = listOf(-1,1,0,0)
val dy = listOf(0,0,-1,1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val nk = br.readLine().split(" ").map { it.toInt() }
    val n = nk[0]
    val k = nk[1]

    var virus = mutableListOf<List<Int>>()
    val graph = List(n) { MutableList(n){0} }

    for (i in 0 until n) {
        val row = br.readLine().split(" ").map { it.toInt() }
        for( j in row.indices){
            if (row[j] != 0)
                virus.add(listOf(row[j],i,j))
            graph[i][j] = row[j]
        }

    }

    val target = br.readLine().split(" ").map{it.toInt()}
    val s = target[0]

    val sortedVirus = virus.sortedBy { it[0] }
    val deque = ArrayDeque<List<Int>>()

    for(i in sortedVirus.indices){
        deque.add(sortedVirus[i])
    }

    bfs(deque,graph,n,s)
    val x = target[1]-1
    val y = target[2]-1
    println(graph[x][y])
}


fun bfs(deque:ArrayDeque<List<Int>>,graph:List<MutableList<Int>>, n:Int,s:Int){

    var time = -1
    var newVirus = 0

    val visited = Array(n) { Array(n) { false } }
    while(deque.isNotEmpty()){
        val now = deque.removeFirst()
        val virusSpecies = now[0]
        val x = now[1]
        val y = now[2]

        if(newVirus != virusSpecies){
            newVirus = virusSpecies
            if(virusSpecies == 1){
                time++
            }
        }

        if(time == s){
            break
        }

        for(i in 0..3){
            val nx = x + dx[i]
            val ny = y + dy[i]

            if(nx<0 || ny<0 || nx>= n || ny>=n){
                continue
            }
            if(graph[nx][ny] == 0 && !visited[nx][ny]){
                visited[nx][ny] = true
                graph[nx][ny] = virusSpecies
                deque.add(listOf(virusSpecies,nx,ny))
            }
        }
    }
}
