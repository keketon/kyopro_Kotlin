/**
 * 遅延評価を加えたセグメント木
 * 区間更新ができる
 *
 * @property n_ セグ木のオリジナルのサイズ
 * @property n セグ木のサイズ
 * @property tree セグ木本体
 * @property lazy 遅延評価用配列
 */
class LazySegmentTree(
    val n_: Int
) {
    // companionにしても良いが、提出環境でバグる可能性があるのでこのまま
    private val INF: Long = 1_000_000_000_000_000_000L
    private val MOD: Long = 998_244_353L

    var n = 1
    var tree = longArrayOf()
    var lazy = longArrayOf()

    init {
        n = 1
        while (n < n_) {
            n *= 2
        }

        tree = LongArray(2 * n - 1) { 0 }
        lazy = LongArray(2 * n - 1) { INF }
    }

    /**
     * 区間の値更新
     *
     * @param begin 元の区間の初め（閉区間）
     * @param end 元の区間の終わり（開区間）
     * @param x 更新する値
     * @param index 今見ているノードの位置
     * @param left tree[index]が表す区間の左端（閉区間）
     * @param right tree[index]が表す区間の右端（開区間）
     */
    fun update(begin: Int, end: Int, x: Long, index: Int = 0, left: Int = 0, right: Int = n) {
        eval(index)
        if (begin <= left && right <= end) {  // 完全に内側の時
            lazy[index] = x
            eval(index)
        } else if (begin < right && left < end) {                     // 一部区間が被る時
            update(begin, end, x, index * 2 + 1, left, (left + right) / 2) // 左の子
            update(begin, end, x, index * 2 + 2, (left + right) / 2, right) // 右の子
            tree[index] = (tree[index * 2 + 1] + tree[index * 2 + 2]) % MOD
        }
    }

    /**
     * [begin, end)の評価値を取得する
     *
     * @param begin 区間初め（閉区間）
     * @param end 区間終わり（開区間）
     * @return 区間の評価値
     */
    fun query(begin: Int, end: Int) = query_sub(begin, end, 0, 0, n)

    /**
     * 評価値取得用のサブクエリ
     *
     * @param begin 元の区間の初め（閉区間）
     * @param end 元の区間の終わり（開区間）
     * @param index 今見ているノードの位置
     * @param left tree[index]が表す区間の左端（閉区間）
     * @param right tree[index]が表す区間の右端（開区間）
     * @return 区間の評価値
     */
    fun query_sub(begin: Int, end: Int, index: Int, left: Int, right: Int): Long {
        eval(index)
        return if (right <= begin || end <= left) { // 範囲外なら考えない
            0L
        } else if (begin <= left && right <= end) { // 範囲内なので自身の値を返す
            tree[index]
        } else { // 一部区間が被る時
            val vl = query_sub(begin, end, index * 2 + 1, left, (left + right) / 2)
            val vr = query_sub(begin, end, index * 2 + 2, (left + right) / 2, right)
            (vl + vr) % MOD
        }
    }

    /**
     * 遅延評価
     *
     * @param index
     */
    fun eval(index: Int) { // 配列のk番目を更新
        if (lazy[index] == INF) return  // 更新するものが無ければ終了
        if (index < n - 1) {             // 葉でなければ子に伝搬
            lazy[index * 2 + 1] = lazy[index]
            lazy[index * 2 + 2] = lazy[index]
        }
        // 自身を更新
        tree[index] = lazy[index]
        lazy[index] = INF
    }
}