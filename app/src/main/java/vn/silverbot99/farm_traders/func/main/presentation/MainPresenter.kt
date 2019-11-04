package vn.silverbot99.farm_traders.func.main.presentation

import vn.silverbot99.core.app.domain.excecutor.AndroidUseCaseExecution
import vn.silverbot99.core.base.domain.interactor.ResultListener
import vn.silverbot99.core.base.domain.interactor.UseCaseTask
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator
import vn.silverbot99.farm_traders.func.main.domain.MainConfigUseCase

class MainPresenter(private val screenNavigator: AndroidScreenNavigator) : MainContract.Presenter() {
    override fun gotoNewsActivity() {
        screenNavigator.gotoNewsActivity()
    }

    override fun gotoGasStoreActivity() {
        screenNavigator.gotoGasolineStoreActivity()
    }

    override fun gotoMedicalActivity() {
        screenNavigator.gotoMedicalActivity()
    }

    private var mainConfigUseCase = MainConfigUseCase(AndroidUseCaseExecution())
    private var useCaseTask: UseCaseTask? = null

    override fun loadConfigData() {
        view?.showLoading()
        useCaseTask = mainConfigUseCase.executeAsync(object : ResultListener<MainConfigUseCase.Output> {
            override fun done() {
                view?.hideLoading()
            }

            override fun fail(errorCode: Int, msgError: String) {
                view?.showError(msgError)
            }

            override fun success(data: MainConfigUseCase.Output) {
                if (!data.success) {
                    view?.showError(data.detail)
                }
                view?.handleAfterLoadingConfig(data)
            }
        }, MainConfigUseCase.Input(""))
    }


    override fun gotoLoginActivity() {
        screenNavigator.gotoPassportActivity()
    }


    override fun showProfileView() {
        screenNavigator.goToProfileActivity()
    }

}