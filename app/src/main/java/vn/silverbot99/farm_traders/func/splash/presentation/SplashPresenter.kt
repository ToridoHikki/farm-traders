package vn.silverbot99.farm_traders.func.splash.presentation

import android.location.LocationManager
import vn.silverbot99.core.base.domain.interactor.UseCaseTask
import vn.silverbot99.farm_traders.app.common.AppConstants
import vn.silverbot99.farm_traders.app.presentation.navigation.ScreenNavigator

class SplashPresenter(private val screenNavigator: ScreenNavigator) : SplashContract.Presenter() {
    override fun gotoSignUpActivity() {
        screenNavigator.gotoSignUpScreen()
    }


//    private var checkVersionAppUseCase = CheckVersionAppUseCase(AndroidUseCaseExecution())
//    private var reloginAppUseCase = ReLoginUseCase(AndroidUseCaseExecution())
    private var task: UseCaseTask? = null

    override fun checkLocation(manager: LocationManager) {
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            view?.showAlertMessageNoGps()
        } else {
            view?.nextActivity()
        }
    }

    override fun registerAppPermission() {
        view?.let {
            for (permission in AppConstants.PERMISSIONS_IN_APP) {
                if (!it.showRequestPermission(permission)) {
                    return
                }
            }
            it.handleAfterRequestPermission()
        }
    }

    override fun gotoMainActivity() {
        screenNavigator.gotoMainActivity()
    }

    override fun gotoLoginActivity() {
        screenNavigator.gotoPassportActivity()
    }

//    override fun reLogin() {
//        task?.cancel()
//        task = reloginAppUseCase.executeAsync(object : ResultListener<PassportResponse> {
//            override fun done() {
//            }
//
//            override fun fail(errorCode: Int, msgError: String) {
//                view?.showErrorDialog(msgError)
//            }
//
//            override fun success(data: PassportResponse) {
//                if (data.success) {
//                    view?.handleAfterReLogin(data)
//                } else {
//                    view?.showErrorDialog(data.detail.getValueOrDefaultIsEmpty())
//                }
//            }
//        }, "")
//    }

}