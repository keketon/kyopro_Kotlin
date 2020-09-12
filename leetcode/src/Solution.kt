class Solution {
    fun isHappy(n: Int): Boolean {
        var tmp = n.toLong()
        val apperred = mutableSetOf<Long>()

        while (!apperred.contains(tmp)) {
            if (tmp == 1L) return true
            apperred.add(tmp)
            tmp = calcHappy(tmp)
            println(tmp)
        }

        return false
    }

    fun calcHappy(n: Long): Long {
        var ret = 0L
        var tmp = n
        while (tmp > 0) {
            ret += (tmp % 10) * (tmp % 10)
            tmp /= 10
        }
        return ret
    }
}