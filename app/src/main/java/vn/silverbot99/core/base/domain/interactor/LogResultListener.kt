package vn.silverbot99.core.base.domain.interactor

import com.orhanobut.logger.Logger
import kotlinex.string.getValueOrDefaultIsEmpty
import retrofit2.HttpException
import vn.silverbot99.core.base.domain.exception.AppException
import java.net.UnknownHostException

class LogResultListener<in T> : RawResultListener<T>() {
    override fun success(data: T) {
    }

    override fun fail(throwable: Throwable) {
        when (throwable) {
            is AppException -> {
                Logger.e(throwable, throwable.errorMessage)
            }
            is UnknownHostException -> {
                Logger.e(throwable, useCaseResourceProvider.getErrorMsgUnknownHostException())
            }
            is HttpException -> {
                var errorMsg = useCaseResourceProvider.getErrorMsg(throwable.code())
                if (throwable.message().getValueOrDefaultIsEmpty().isNotEmpty()) {
                    errorMsg += ": " + throwable.message().getValueOrDefaultIsEmpty()
                }
                Logger.e(throwable, errorMsg)
            }
            is Exception -> {
                Logger.e(throwable, useCaseResourceProvider.getErrorMsg(throwable.message))
            }
            else -> {
                Logger.e(throwable, useCaseResourceProvider.getErrorMsg())
            }
        }

    }

    override fun done() {
    }

}