package implement.problem9

import kotlin.math.min

fun main(){
    val s = "aaaaaaaaaabbbbbbbbbb"
    var minCount = s.length
    for(i in 1..s.length/2) {
        val slicedS = s.chunked(i)
        var compared = slicedS[0]
        var count = i
        var number = 1
        for(j in 1 until slicedS.size){
            if (compared == slicedS[j]){
                number++
                continue
            }
            if(number != 1) {
                count += number.toString().length
                number = 1
            }
            compared = slicedS[j]
            count += slicedS[j].length
        }
        if(number != 1) {
            count += number.toString().length
        }
        minCount = min(minCount, count)
    }
    println(minCount)
}