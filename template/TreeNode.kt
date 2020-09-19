class TreeNode(var `val`: Int) {

    var left: TreeNode? = null
    var right: TreeNode? = null

    // TODO: 2020/09/13 木構造っぽく出力するには工夫が必要そう
    // Factoryの逆をやってlistを生成するのがコスパ良さそう
    // null も表示した方が良い
    override fun toString(): String {
        return `val`.toString() +
                (if (left != null) ", " + left.toString() else "") +
                (if (right != null) ", " + right.toString() else "")
    }

    // usefun functions
    companion object{
        fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
            if (p == null && q == null) return true
            if (p == null || q == null || p.`val` != q.`val`) return false

            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right)

        }

        fun toPreorderList(root: TreeNode?): List<Int> {
            if (root == null) return listOf()
            val preorderList = mutableListOf<Int>()
            preorderList.addAll(toPreorderList(root.left))
            preorderList.add(root.`val`)
            preorderList.addAll(toPreorderList(root.right))
            return preorderList
        }
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