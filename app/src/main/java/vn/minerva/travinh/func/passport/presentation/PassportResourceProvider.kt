package vn.minerva.travinh.func.passport.presentation

import vn.minerva.core.base.domain.provider.AndroidResourceProvider
import vn.minerva.travinh.R

class PassportResourceProvider : AndroidResourceProvider() {
    fun getErrorTextNotEmpty(): String {
        return resourceManager.getString(R.string.msg_text_empty)
    }

    fun getForceLoginUserNotSupport(): String {
        return resourceManager.getString(R.string.text_force_login_user_not_support)
    }
}