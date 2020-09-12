fun main(args: Array<String>) {
    // val x = readLine()!!.split(',').map { it.toInt() }.toIntArray()
    // val x = readLine()!!.toInt()

    val x = readLine()!!.toInt()

    val sol = Solution()
    val ans = sol.isHappy(x)

    println(ans.toString())
}