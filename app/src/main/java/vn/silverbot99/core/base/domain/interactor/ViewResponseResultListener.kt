package vn.silverbot99.core.base.domain.interactor

import retrofit2.HttpException
import vn.silverbot99.core.app.util.ConstApp
import java.net.ConnectException
import java.net.UnknownHostException


class ViewResponseResultListener<in T>(private val resultListener: ResultListener<T>) : RawResultListener<T>() {
    override fun success(data: T) {
        resultListener.success(data)
    }

    override fun fail(throwable: Throwable) {
        when (throwable) {
            is UnknownHostException -> {
                resultListener.fail(ConstApp.ERROR_CODE_UNKNOWN_HOST, useCaseResourceProvider.getErrorMsgUnknownHostException())
            }
            is HttpException -> {
                val errorMsg = useCaseResourceProvider.getErrorMsg(throwable.code())
                resultListener.fail(ConstApp.ERROR_CODE_HTTP, errorMsg)
            }
            is ConnectException -> {
                resultListener.fail(ConstApp.ERROR_CODE_UNKNOWN_HOST, useCaseResourceProvider.getErrorMsgUnknownHostException())
//                resultListener.fail(ConstApp.ERROR_CODE_CONNECT, useCaseResourceProvider.getErrorMsg(throwable.message))
            }
            else -> {
                resultListener.fail(ConstApp.ERROR_CODE_DEFAULT, useCaseResourceProvider.getErrorMsg())
            }
        }
    }

    override fun done() {
        resultListener.done()
    }

}