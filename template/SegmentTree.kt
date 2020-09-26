/**
 * セグメント木
 *
 * @property n_ セグ木のオリジナルのサイズ
 * @property n セグ気の葉の数
 * @property tree セグ木本体
 */
class SegmentTree(
    val n_: Int
) {

    // companionにしても良いが、提出環境でバグる可能性があるのでこのまま
    private val INF: Long = 1_000_000_000_000_000_000L
    private val MOD: Long = 998_244_353L

    var n = 1
    var tree = longArrayOf()

    init {
        n = 1
        while (n < n_) {
            n *= 2
        }

        tree = LongArray(2 * n - 1) { 0L }
    }

    /**
     * 値の更新
     *
     * @param index 更新するインデックス
     * @param x 更新する値
     */
    fun update(index: Int, x: Long) {
        var cnt = index + n - 1
        tree[cnt] = x
        while (cnt > 0){
            cnt = (cnt - 1) / 2
            tree[cnt] = (tree[cnt * 2 + 1] + tree[cnt * 2 + 2]) % MOD
        }
    }

    /**
     * [begin, end)で評価値の取得
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
}