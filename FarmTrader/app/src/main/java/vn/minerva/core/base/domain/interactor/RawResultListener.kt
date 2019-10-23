package vn.minerva.core.base.domain.interactor

abstract class RawResultListener<in T> {
    val useCaseResourceProvider = UseCaseResourceProvider()
    abstract fun success(data: T)
    abstract fun fail(throwable: Throwable)
    abstract fun done()
}