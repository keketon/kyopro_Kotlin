/**
 * RMQ(区間の最小値を求めるセグ木)
 *
 * @property n セグ木のサイズ
 * @property tree セグ木本体
 */
class RmqSegmentTree(
    val n: Int,
    val tree: LongArray = LongArray(2 * n - 1)
) {

    // companionにしても良いが、提出環境でバグる可能性があるのでこのまま
    private val INF: Long = 1_000_000_000_000_000_000

    /**
     * セグメント木の更新
     *
     * @param index 更新するインデックス
     * @param x 更新する値
     */
    fun update(index: Int, x: Long) {
        var i = index
        i += n - 1
        tree[i] = x
        while (i > 0) {
            i = (i - 1) / 2
            tree[i] = min(tree[i * 2 + 1], tree[i * 2 + 2])
        }
    }

    /**
     * [begin, end)の最小値を取得する
     *
     * @param begin 区間初め（閉区間）
     * @param end 区間終わり（開区間）
     * @return 区間の最小値
     */
    fun query(begin: Int, end: Int) = query_sub(begin, end, 0, 0, n)

    /**
     * 最小値取得用のサブクエリ
     *
     * @param begin 元の区間の初め（閉区間）
     * @param end 元の区間の終わり（開区間）
     * @param k 今見ているノードの位置
     * @param l tree[k]が表す区間の左端（閉区間）
     * @param r tree[k]が表す区間の右端（開区間）
     * @return 区間の最小値（区間外ならINF）
     */
    fun query_sub(begin: Int, end: Int, k: Int, l: Int, r: Int): Long =
        if (r <= begin || end <= l) { // 範囲外なら考えない
            this.INF
        } else if (begin <= l && r <= end) { // 範囲内なので自身の値を返す
            tree[k]
        } else { // 一部区間が被る時
            val vl = query_sub(begin, end, k * 2 + 1, l, (l + r) / 2)
            val vr = query_sub(begin, end, k * 2 + 2, (l + r) / 2, r)
            min(vl, vr)
        }
}