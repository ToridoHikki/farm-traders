package vn.silverbot99.farm_traders.func.passport.presentation

import vn.silverbot99.core.base.domain.provider.AndroidResourceProvider
import vn.silverbot99.farm_traders.R

class SignUpResourceProvider : AndroidResourceProvider() {
    fun getErrorTextNotEmpty(): String {
        return resourceManager.getString(R.string.msg_text_empty)
    }

    fun getForceLoginUserNotSupport(): String {
        return resourceManager.getString(R.string.text_force_login_user_not_support)
    }
}