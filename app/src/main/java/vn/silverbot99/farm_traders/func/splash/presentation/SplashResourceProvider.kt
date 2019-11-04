package vn.silverbot99.farm_traders.func.splash.presentation

import vn.silverbot99.core.app.util.Utils
import vn.silverbot99.core.base.domain.provider.AndroidResourceProvider
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity

class SplashResourceProvider(private val mvpActivity: MvpActivity) : AndroidResourceProvider() {

    fun getVersionApp(): String {
        return "Version ${Utils.getVersionName(mvpActivity)} (${Utils.getVersionCode(mvpActivity)})"
    }
}