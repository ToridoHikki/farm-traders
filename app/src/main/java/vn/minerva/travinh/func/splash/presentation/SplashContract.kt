package vn.minerva.travinh.func.splash.presentation

import android.location.LocationManager
import vn.minerva.core.base.presentation.mvp.base.MvpPresenter
import vn.minerva.core.base.presentation.mvp.base.MvpView
import vn.minerva.travinh.app.data.network.response.AppVersionResponse
import vn.minerva.travinh.app.data.network.response.PassportResponse

interface SplashContract {

    interface View : MvpView {
        fun showRequestPermission(permission: String): Boolean
        fun showAlertMessageNoGps()
        fun handleAfterLoadAppVersion(data: AppVersionResponse)
        fun handleAfterRequestPermission()
        fun handleAfterReLogin(data: PassportResponse)
        fun showErrorDialog(msg: String)
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun loadAppVersion()

        abstract fun gotoMainActivity()

        abstract fun gotoLoginActivity()

        abstract fun registerAppPermission()

        abstract fun reLogin()

        abstract fun checkLocation(manager: LocationManager)
    }
}