import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val TEST_CASE_FILE_NAME = "leetcode/resources/testCase.txt"
    val br = File(TEST_CASE_FILE_NAME).bufferedReader()


    while (true) {
        val startTime = System.currentTimeMillis()

        // ---process start---
        val input = br.readLine() ?: break

//        val x = input.split(',').map { it.toInt() }.toIntArray()
//        val x = input.toInt()
        val x = input.toInt()

        val sol = Solution()
        val ans = sol.xxx(x)

        println(ans.toString())
        // ---process end---

        val endTime = System.currentTimeMillis()
        val processTime = endTime - startTime

        println("Completed in $processTime ms")
    }
}