import kotlin.random.Random

class QuickSelect {

    private var freq: MutableMap<Int, Int> = mutableMapOf()

    // for LeetCode 347
    fun topKFrequent(nums: IntArray, k: Int): IntArray {

        freq = mutableMapOf()
        nums.forEach {
            freq[it] = freq.getOrDefault(it, 0) + 1
        }

        val noDupNums = freq.keys.toIntArray()

        val n = noDupNums.size

        quickSelect(noDupNums, 0, n - 1, n - k)

        return noDupNums.sliceArray(n - k until n)

    }

    private fun quickSelect(nums: IntArray, left: Int, right: Int, objective: Int) {

        if (left == right) return

        val pivIdx = lomutoPertition(nums, left, right)

        when {
            pivIdx == objective -> return
            pivIdx < objective -> quickSelect(nums, pivIdx + 1, right, objective)
            else -> quickSelect(nums, left, pivIdx - 1, objective)
        }
    }

    // freq[xx] must be xx. freq is for leetCode's problem
    private fun lomutoPertition(nums: IntArray, left: Int, right: Int): Int {

        // if we set pivot index as `right`, the time complexity may go bad in the case of sorting
        val pivIdx = Random.nextInt(left, right + 1)
        val piv = freq[nums[pivIdx]]!!

        swap(nums, pivIdx, right)

        var fstBiggerIdx = left

        for (i in left until right) {
            if (freq[nums[i]]!! < piv) {
                swap(nums, i, fstBiggerIdx)
                fstBiggerIdx++
            }
        }

        swap(nums, fstBiggerIdx, right)

        return fstBiggerIdx
    }

    private fun swap(nums: IntArray, a: Int, b: Int) {
        val tmp = nums[a]
        nums[a] = nums[b]
        nums[b] = tmp
    }
}
