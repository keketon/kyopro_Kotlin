class BinarySearch(private val nums: IntArray = intArrayOf(), private val target: Int = 0) {

    fun binarySearch(): Int {
        var left = -1
        var right = nums.size
        while (right - left > 1) {
            val mid = left + (right - left) / 2
            if (isOK(mid)) right = mid else left = mid
        }
        return right
    }

    private fun isOK(mid: Int): Boolean {
        return nums[mid] >= target
    }
}