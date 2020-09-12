class MyMod(var value: Long = 0L) {
  operator fun plus(a: Long) {
    value = plus(value, a)
  }

  operator fun minus(a: Long) {
    value = minus(value, a)
  }

  operator fun times(a: Long) {
    value = times(value, a)
  }

  operator fun div(a: Long) {
    value = div(value, a)
  }

  operator override fun equals(other: Any?): Boolean {
    // FIXME: 2020/08/30 定義途中
    return super.equals(other)
  }

  companion object {
    const val MOD: Long = 1000000007L
    fun plus(a: Long, b: Long): Long {
      return (a % MOD + b % MOD) % MOD
    }

    fun minus(a: Long, b: Long): Long {
      return (a % MOD - b % MOD + MOD) % MOD
    }

    fun times(a: Long, b: Long): Long {
      return (a % MOD) * (b % MOD) % MOD
    }

    fun div(a: Long, b: Long): Long {
      return (a % MOD) * (pow(b, MOD - 2) % MOD) % MOD
    }

    // calc a^b
    // b <= 0 は1を返す
    fun pow(a: Long, b: Long): Long {
      var a = a
      var b = b
      var result = 1L
      a %= MOD
      while (b > 0) {
        if (b % 2 == 1L) result = result * a % MOD
        a = a * a % MOD
        b = b shr 1
      }
      return result
    }

    // calc inverse element
    fun invEle(a: Long): Long {
      return pow(a % MOD, MOD - 2) % MOD
    }
  }

}