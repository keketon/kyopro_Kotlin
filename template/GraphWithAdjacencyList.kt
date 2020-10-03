class GraphWithAdjacencyList(n: Int) {
    val adjacencyList  = Array(n) { mutableListOf<Int>() }

    fun addEdge(from: Int, to: Int, isDirected :Boolean = false) {
        adjacencyList[from].add(to)
        if (!isDirected) adjacencyList[to].add(from)
    }
}