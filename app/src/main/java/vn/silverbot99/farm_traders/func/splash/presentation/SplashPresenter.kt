package vn.silverbot99.farm_traders.func.splash.presentation

import android.location.LocationManager
import kotlinex.string.getValueOrDefaultIsEmpty
import vn.silverbot99.core.app.domain.excecutor.AndroidUseCaseExecution
import vn.silverbot99.core.base.domain.interactor.ResultListener
import vn.silverbot99.core.base.domain.interactor.UseCaseTask
import vn.silverbot99.farm_traders.app.common.AppConstants
import vn.silverbot99.farm_traders.app.data.network.response.AppVersionResponse
import vn.silverbot99.farm_traders.app.data.network.response.PassportResponse
import vn.silverbot99.farm_traders.app.presentation.navigation.ScreenNavigator
import vn.silverbot99.farm_traders.func.splash.domain.CheckVersionAppUseCase
import vn.silverbot99.farm_traders.func.splash.domain.ReLoginUseCase

class SplashPresenter(private val screenNavigator: ScreenNavigator) : SplashContract.Presenter() {


    private var checkVersionAppUseCase = CheckVersionAppUseCase(AndroidUseCaseExecution())
    private var reloginAppUseCase = ReLoginUseCase(AndroidUseCaseExecution())
    private var task: UseCaseTask? = null
    override fun loadAppVersion() {
        task?.cancel()
        task = checkVersionAppUseCase.executeAsync(object : ResultListener<AppVersionResponse> {
            override fun done() {
            }

            override fun fail(errorCode: Int, msgError: String) {
                view?.showErrorDialog(msgError)
            }

            override fun success(data: AppVersionResponse) {
                view?.handleAfterLoadAppVersion(data)
            }
        }, "")

    }

    override fun checkLocation(manager: LocationManager) {
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            view?.showAlertMessageNoGps()
        } else {
            loadAppVersion()
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

    override fun reLogin() {
        task?.cancel()
        task = reloginAppUseCase.executeAsync(object : ResultListener<PassportResponse> {
            override fun done() {
            }

            override fun fail(errorCode: Int, msgError: String) {
                view?.showErrorDialog(msgError)
            }

            override fun success(data: PassportResponse) {
                if (data.success) {
                    view?.handleAfterReLogin(data)
                } else {
                    view?.showErrorDialog(data.detail.getValueOrDefaultIsEmpty())
                }
            }
        }, "")
    }

}