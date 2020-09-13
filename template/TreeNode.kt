class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    // TODO: 2020/09/13 木構造っぽく出力するには工夫が必要そう
    // Factoryの逆をやってlistを生成するのがコスパ良さそう
    override fun toString(): String {
        return `val`.toString() +
                (if (left != null) ", " + left.toString() else "") +
                (if (right != null) ", " + right.toString() else "")
    }
}

class TreeNodeFactory {

    companion object {
        fun new(inputArray: List<String>): TreeNode? {

            if (inputArray.isEmpty()) return null
            if (inputArray.first() == "null") return null

            val root = TreeNode(inputArray.first().toInt())
            val que: Queue<TreeNode> = LinkedList(listOf(root))
            var cnt = 1
            while (cnt < inputArray.size && que.isNotEmpty()) {
                val nowNode = que.poll()

                val l = if (inputArray[cnt] == "null") null else inputArray[cnt].toInt()
                if (l != null) {
                    val leftTree = TreeNode(l)
                    nowNode.left = leftTree
                    que.add(leftTree)
                }

                cnt++
                if (cnt >= inputArray.size) break

                val r = if (inputArray[cnt] == "null") null else inputArray[cnt].toInt()
                if (r != null) {
                    val rightTree = TreeNode(r)
                    nowNode.right = rightTree
                    que.add(rightTree)
                }

                cnt++
            }

            return root
        }
    }
}
