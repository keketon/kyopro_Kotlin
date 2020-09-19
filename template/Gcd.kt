class Gcd {
    fun gcd(a: Int, b: Int): Int {
        if (b > a) return gcd(b, a)
        return if (b == 0) a else gcd(b, a % b)
    }

    fun gcd(a: Long, b: Long): Long {
        if (b > a) return gcd(b, a)
        return if (b == 0L) a else gcd(b, a % b)
    }
}