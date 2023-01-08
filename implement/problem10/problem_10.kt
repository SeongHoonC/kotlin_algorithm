package implement.problem10


class Solution {
    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        val m = key.size // 키의 한변
        val n = lock.size // 자물쇠의 한변
        val newLock = Array(3 * n ){ IntArray(3 * n)} // 뉴 자물쇠
        repeat(n){ row ->
            repeat(n){ col ->
                newLock[n + row][n + col] = lock[row][col] // n 부터 2n 앞까지
            }
        }
        var mKey = key
        repeat(4){
            if(check(mKey, newLock)){
                return true
            }
            mKey = rotate(key)
        }
        return false
    }

    fun rotate(key: Array<IntArray>) : Array<IntArray> {
        val n = key.size
        val rotateArray = Array(n){IntArray(n)}
        repeat(n){ row ->
            repeat(n){ col ->
                rotateArray[col][n - row - 1] = key[row][col]
            }
        }
        return rotateArray
    }

    // 비교를 해야하는데,
    // 새로운 자물쇠 안에서 키를 넣어봄 -> 기존 자물쇠인 부분이 1인 경우 참,
    fun check(key: Array<IntArray>, lock: Array<IntArray>) : Boolean{
        val n = lock.size
        val m = key.size

        for(row in 0 until n - m){
            for (col in 0 until  n - m){ // 시작점 세팅
                for(i in key.indices) { // 새로운 자물쇠에 키를 넣음
                    for (j in key.indices) {
                        lock[row + i][col + j] += key[i][j]
                    }
                }
                if (canOpenDoor(lock)) return true // 키를 확인
                for(i in key.indices) { // 지도 원상 복귀
                    for (j in key.indices) {
                        lock[row + i][col + j] -= key[i][j]
                    }
                }
            }
        }
        return false
    }

    // 모든 lock이 1인지 확인하는 함수
    fun canOpenDoor(lock: Array<IntArray>) : Boolean{
        val n = lock.size
        val smallRow = n / 3
        for(i in smallRow until smallRow * 2) { // 기존 자물쇠의 요소가 다 1인지 확인
            for (j in smallRow until smallRow * 2) {
                if(lock[i][j] != 1){
                    return false
                }
            }
        }
        return true
    }
}

fun main(){
    val solution = Solution()
    val key : Array<IntArray> = arrayOf(intArrayOf(0,0,0),intArrayOf(1,0,0),intArrayOf(0,1,1))
    val lock : Array<IntArray> = arrayOf(intArrayOf(1,1,1),intArrayOf(1,1,0),intArrayOf(1,0,1))
    solution.solution(key, lock)
}