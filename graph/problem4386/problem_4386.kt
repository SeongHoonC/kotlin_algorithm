package graph.problem4386

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val v = br.readLine().toInt()
    val parent = IntArray(v + 1) { it }
    val edges = mutableListOf<Triple<Double,Int,Int>>()
    val nodes = mutableListOf(Pair(0.0, 0.0))

    for (i in 0 until v) {
        val (x, y) = br.readLine().split(" ").map { it.toDouble() }
        nodes.add(Pair(x, y))
    }

    for (i in 1 until v) {
        for (j in i + 1 until v + 1) {
            val dist = distance(nodes[i],nodes[j])
            edges.add(Triple(dist,i,j))
        }
    }

    edges.sortWith(compareBy { it.first })

    println("%.2f".format(kruskal(edges,parent)))
}

fun distance(node1: Pair<Double, Double>, node2: Pair<Double, Double>): Double {
    return sqrt((node1.first - node2.first).pow(2) + (node1.second - node2.second).pow(2))
}

fun findParent(parent: IntArray, x: Int): Int {
    if (parent[x] != x) {
        parent[x] = findParent(parent, parent[x])
    }
    return parent[x]
}

fun unionParent(parent: IntArray, a: Int, b: Int) {
    val aParent = findParent(parent, a)
    val bParent = findParent(parent, b)
    if (aParent < bParent) {
        parent[bParent] = aParent
    } else {
        parent[aParent] = bParent
    }
}


fun kruskal(edges:MutableList<Triple<Double,Int,Int>>,parent:IntArray):Double {
    var result = 0.0
    for(i in edges.indices){
        val (cost, a, b) = edges[i]
        if (findParent(parent,a) != findParent(parent,b)){
            unionParent(parent, a, b)
            result += cost
        }
    }
    return result
}

