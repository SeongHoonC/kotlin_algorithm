package implement.cheese2636

val dx = listOf(-1,1,0,0)
val dy = listOf(0,0,-1,1)

fun main(){
    val input1 = readLine()
    val nm = input1?.split(" ")
    val n = nm?.get(0)!!.toInt()
    val m = nm[1].toInt()
    var cheese = makeCheese(n)
    var result = listOf<Pair<Int,Int>>()
    var count = -1
    while(true) {
        count++
        val copiedCheese = cheese.map{it.toMutableList()}.toMutableList()
        val edge = bfs(0, 0, copiedCheese, n, m).toList()
        if(edge.isEmpty()){
            println(count)
            println(result.size)
            break
        }
        for(i in edge.indices){
            val x = edge[i].first
            val y = edge[i].second
            cheese[x][y] = 0
        }
        result = edge
    }
}



fun bfs(x:Int, y:Int, mutableCheese:MutableList<MutableList<Int>>, n:Int, m:Int):MutableSet<Pair<Int,Int>>{
    var deque = ArrayDeque<Pair<Int,Int>>()
    var edge = mutableSetOf<Pair<Int,Int>>()
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
            if(mutableCheese[nx][ny] == 1){
                edge.add(Pair(nx,ny))
                continue
            }
            if(mutableCheese[nx][ny] == 0){
                mutableCheese[nx][ny] = 2
                deque.add(Pair(nx,ny))
            }
        }
    }
    return edge
}

fun makeCheese(n:Int):MutableList<MutableList<Int>>{
    val cheese = mutableListOf<MutableList<Int>>()
    for(i in 0 until n){
        val input2 = readLine()
        val row = input2!!.split(" ").map{it.toInt()}
        row.toMutableList().let { cheese.add(it) }
    }
    return cheese
}