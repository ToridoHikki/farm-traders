package vn.silverbot99.farm_traders.func.splash.presentation

import android.location.LocationManager
import vn.silverbot99.core.base.presentation.mvp.base.MvpPresenter
import vn.silverbot99.core.base.presentation.mvp.base.MvpView

interface SplashContract {

    interface View : MvpView {
        fun showRequestPermission(permission: String): Boolean
        fun showAlertMessageNoGps()
        fun handleAfterRequestPermission()
//        fun showErrorDialog(msg: String)
        fun nextActivity()
    }

    abstract class Presenter : MvpPresenter<View>() {
//        abstract fun loadAppVersion()

        abstract fun gotoMainActivity()

        abstract fun gotoLoginActivity()

        abstract fun gotoSignUpActivity()


        abstract fun registerAppPermission()

//        abstract fun reLogin()

        abstract fun checkLocation(manager: LocationManager)
    }
}