package vn.minerva.core.base.domain.interactor

import vn.minerva.core.base.domain.manager.AndroidResourceManager
import vn.minerva.core.base.domain.provider.ResourceProvider
import vn.minerva.travinh.R

class UseCaseResourceProvider : ResourceProvider(AndroidResourceManager()) {
    fun getErrorMsg(errorCode: Int): String {
        return resourceManager.getString(R.string.warning_check_server_again)
    }

    fun getErrorMsg(): String {
        return resourceManager.getString(R.string.error_system_msg)
    }

    fun getErrorMsgUnknownHostException(): String {
        return resourceManager.getString(R.string.error_internet_connect)
    }

    fun  getErrorMsg(errorMsg: String?): String {
        val errorMsgExt = if (errorMsg != null)  ": $errorMsg" else ""
        return resourceManager.getString(R.string.error_system_msg) + errorMsgExt
    }
}