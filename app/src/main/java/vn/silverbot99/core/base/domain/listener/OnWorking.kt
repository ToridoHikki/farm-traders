package vn.silverbot99.core.base.domain.listener

@FunctionalInterface
interface OnWorking<out T> {
    fun work(): T
}
