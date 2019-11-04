package vn.silverbot99.core.base.domain.interactor

interface ResultListener<in T> {
    fun success(data: T)
    fun fail(errorCode: Int, msgError: String)
    fun done()
}