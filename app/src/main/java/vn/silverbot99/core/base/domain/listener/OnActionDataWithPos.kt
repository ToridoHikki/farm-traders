package vn.silverbot99.core.base.domain.listener

@FunctionalInterface
interface OnActionDataWithPos<in T> {
    fun onAction(data: T, pos: Int)
}
