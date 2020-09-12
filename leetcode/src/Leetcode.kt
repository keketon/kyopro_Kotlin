fun main(args: Array<String>) {
    // val x = readLine()!!.split(',').map { it.toInt() }.toIntArray()
    // val x = readLine()!!.toInt()

    val x = readLine()!!.split(',').map { it.toInt() }.toIntArray()

    val sol = Solution()
    val ans = sol.maxProfit(x)

    println(ans.toString())
}