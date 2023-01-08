package implement.problem7

fun main(){
    val input = readLine()
    val n = input?.chunked(1)?.map{it.toInt()}
    var sumFront = 0
    var sumBack = 0
    if (n != null) {
        for(i in 0 until n.size/2){
            sumFront += n[i]
            sumBack += n[n.size-1-i]
        }
    }
    if(sumFront == sumBack){
        println("LUCKY")
        return
    }
    println("READY")
}