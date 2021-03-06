class UnionFind(n: Int) {
    val parent: IntArray = IntArray(n) { it }
    val rank: IntArray = IntArray(n) { 1 }

    fun find(i: Int): Int {
        val p = parent[i]
        return if (i == p) i
        else find(p).also {
            parent[i] = it
            rank[i] = 2
        }
    }

    fun union(i: Int, j: Int) {
        val root1 = find(i)
        val root2 = find(j)
        if (root1 == root2) return
        when {
            rank[root1] >= rank[root2] -> {
                parent[root2] = root1
                rank[root2] += rank[root1]
            }
            else -> {
                parent[root1] = root2
                rank[root1] += rank[root2]
            }
        }
    }
}