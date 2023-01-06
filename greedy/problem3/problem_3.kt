package greedy.problem3

fun main(){
    val input = readLine()
    val first = input?.get(0)
    val splitString = first?.let { input?.split(it) }
    val count = splitString?.count{it.isNotBlank()}
    println(count)
}