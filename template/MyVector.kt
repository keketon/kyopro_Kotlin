/**
 * represen a point or a vector on 2D-plane
 *
 * @property x
 * @property y
 */
data class MyVector(
    var x: Long,
    var y: Long
) {

    constructor(x: Int, y: Int) : this(x = x.toLong(), y = y.toLong())

    fun plus(x: Long, y: Long) {
        this.x += x
        this.y += y
    }

    operator fun plus(v: MyVector) {
        plus(v.x, v.y)
    }

    fun minus(x: Long, y: Long) {
        this.x -= x
        this.y -= y
    }

    operator fun minus(v: MyVector) {
        minus(v.x, v.y)
    }

    fun scalarTimes(m: Long) {
        x *= m
        y *= m
    }

    operator fun times(v: MyVector): Long = x * v.x + y * v.y

    fun squareMagnitude(): Long = this.times(this)

    fun magnitude(): Double = sqrt(squareMagnitude().toDouble())
}
