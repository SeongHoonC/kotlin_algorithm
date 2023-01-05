package stack.tower

import java.util.*

fun laser(n: Int, towers:List<Int>) {
    val stack = Stack<Pair<Int,Int>>()
    for (i in 0 until n) {
        while(true){
            if(stack.empty()){
                print("0 ")
                stack.push(Pair(i+1,towers[i]))
                break
            }
            if(stack.peek().second > towers[i] ){
                print("${stack.peek().first} ")
                stack.push(Pair(i+1,towers[i]))
                break
            }
            stack.pop()
        }
    }
}

fun main() {
    val n = readLine()?.toInt()
    val input = readLine()
    val towers = input?.split(" ")?.map{it.toInt()}
    if (n != null) {
        if (towers != null) {
            laser(n, towers)
        }
    }
}