package kotlinex.exception

import vn.minerva.core.base.domain.exception.AppException


fun Exception.reThrow(errorMessage: String, errorCode: Int = 0) {
    throw AppException(errorMessage, errorCode, this)
}