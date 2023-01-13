package bfs_dfs.problem18

import implement.cheese2636.makeCheese
import java.util.*

fun main() {
    val p = "(()())()"
    println(makeRight(p))
}

fun makeRight(p: String): String {
    if (p == "") {
        return ""
    }
    var breakPoint = 0

    val findBreakPoint = mutableListOf<Char>()
    findBreakPoint.add(p[0])
    for (i in 1 until p.length) {
        findBreakPoint.add(p[i])
        if (findBreakPoint.count { it == '(' } == findBreakPoint.count { it == ')' }) {
            breakPoint = i
            break
        }
    }

    val u = p.substring(0..breakPoint)
    val v = p.substring(breakPoint + 1 until p.length)

    if (checkRight(u)) {
        return u + makeRight(v)
    }

    var newString = "("
    newString = newString + makeRight(v) + ")"
    for (index in 1 until u.length - 1) {
        if (u[index] == ')') {
            newString += '('
            continue
        }
        newString += ')'
    }
    return newString
}

fun checkRight(u: String): Boolean {
    val stack = Stack<Char>()
    stack.push(u[0])
    for (i in 1 until u.length) {
        while (true) {
            if (stack.empty()) {
                return true
            }
            if (stack.peek() == ')') {
                stack.push(u[i])
                break
            }
            if (stack.peek() == u[i]) {
                stack.push(u[i])
                break
            }
            stack.pop()
        }
    }
    return false
}