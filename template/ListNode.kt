class ListNode(var `val`: Int) {
    constructor(`val`: Int, next: ListNode?) : this(`val`) {
        this.next = next
    }

    var next: ListNode? = null

    override fun toString(): String {
        var ret = "["
        var now :ListNode? = this
        while(now != null){
            ret += now.`val`.toString() + ", "
            now = now.next
        }
        return ret.substring(0, ret.length - 2) + ']'
    }
}

class ListNodeFactory {
    companion object {
        fun new(inputArray: IntArray): ListNode? {

            val ret = ListNode(0)
            var now = ret
            inputArray.forEach {
                val next = ListNode(it)
                now.next = next
                now = next
            }
            return ret.next
        }
    }
}
