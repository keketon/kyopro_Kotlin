import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.lang.Exception
import java.util.*

fun main(args: Array<String>) {
    val TEST_CASE_FILE_NAME = "leetcode/resources/testCase.txt"
    val br = File(TEST_CASE_FILE_NAME).bufferedReader()

//    val x = br.readLine()!!.split(',').map { it.toInt() }.toIntArray()
//    val x = br.readLine()!!.toInt()

    val x = br.readLine()!!.toInt()

    val sol = Solution()
    val ans = sol.xxx(x)

    println(ans.toString())
}