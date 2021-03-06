package kotlinex.number

import vn.silverbot99.core.app.util.DecimalUtils
import java.util.*

fun Int?.getValueOrDefaultWithValue(value: Int): Int {
    return this ?: value
}
fun Int?.getValueOrDefaultIsZero(): Int {
    return this ?: 0
}

fun Int.formatNumber(): String {
    return DecimalUtils.decimalFormat(this.toDouble(), 0, Locale.ENGLISH, DecimalUtils.Companion.GroupDecimalMode.COMMA, DecimalUtils.Companion.AfterDotMode.IF_EXIST)
}