package binary_search.problem30


fun main() {
    val words = arrayOf("frodo", "front", "frost", "frozen", "frame", "kakao")
    val queries = arrayOf("??odo","fro??", "????o", "fr???", "fro???", "pro?", "??odo")
    val array = solution(words, queries)
    for (i in queries.indices) {
        println(array[i])
    }
}

fun countByRange(arr: MutableList<String>, leftValue: String, rightValue: String): Int {
    val rightIndex = bisectRight(arr, rightValue)
    val leftIndex = bisectLeft(arr, leftValue)
    return rightIndex - leftIndex
}

fun bisectRight(arr: MutableList<String>, rightValue: String): Int {
    var min = 0
    var max = arr.size -1
    var mid = -1
    while (min <= max) {
        mid = (min + max) / 2
        if (rightValue >= arr[mid]) {
            min = mid + 1
        } else {
            max = mid - 1
        }

        if(min >= arr.size){
            mid = arr.size
            break
        }
    }
    return mid
}

fun bisectLeft(arr: MutableList<String>, leftValue: String): Int {
    var min = 0
    var max = arr.size - 1
    var mid = -1
    while (min <= max) {
        mid = (min + max) / 2
        if (leftValue < arr[mid]) {
            max = mid - 1
        } else {
            min = mid + 1
        }
    }
    return mid
}


fun solution(words: Array<String>, queries: Array<String>): IntArray {
    var answer = IntArray(queries.size)
    val array = Array(10001){ mutableListOf<String>() }
    val reversedArray = Array(10001){ mutableListOf<String>() }
    for (i in words.indices) {
        array[words[i].length].add(words[i])
        reversedArray[words[i].length].add(words[i].reversed())
    }

    for (i in words.indices) {
        if (array[i].size == 0) {
            continue
        }
        array[i].sortWith(compareBy { it })
        reversedArray[i].sortWith(compareBy { it })
    }

    for (i in queries.indices) {
        var sa = queries[i].toCharArray()
        var sz = queries[i].toCharArray()
        for (j in sa.indices) {
            if (sa[j] == '?') {
                sa[j] = 'a'
            }
        }
        for (j in sz.indices) {
            if (sz[j] == '?') {
                sz[j] = 'z'
            }
        }

        if (queries[i][0] != '?') {
            answer[i] = countByRange(array[queries[i].length], String(sa), String(sz))
        } else {
            answer[i] = countByRange(reversedArray[queries[i].length], String(sa).reversed(), String(sz).reversed())
        }
    }
    return answer
}

