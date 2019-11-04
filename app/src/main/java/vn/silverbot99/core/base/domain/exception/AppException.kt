package vn.silverbot99.core.base.domain.exception

class AppException(val errorMessage: String, val errorCode: Int = 1, cause: Throwable? = null) : Exception(errorMessage, cause)