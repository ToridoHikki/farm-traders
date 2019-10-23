package vn.minerva.travinh.func.splash.presentation

import vn.minerva.core.app.util.Utils
import vn.minerva.core.base.domain.provider.AndroidResourceProvider
import vn.minerva.core.base.presentation.mvp.android.MvpActivity

class SplashResourceProvider(private val mvpActivity: MvpActivity) : AndroidResourceProvider() {

    fun getVersionApp(): String {
        return "Version ${Utils.getVersionName(mvpActivity)} (${Utils.getVersionCode(mvpActivity)})"
    }
}