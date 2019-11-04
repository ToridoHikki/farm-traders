package kotlinex.exception

import vn.silverbot99.core.base.domain.exception.AppException


fun Exception.reThrow(errorMessage: String, errorCode: Int = 0) {
    throw AppException(errorMessage, errorCode, this)
}