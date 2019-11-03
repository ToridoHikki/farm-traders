package vn.minerva.travinh.func.main.presentation

import vn.minerva.core.app.domain.excecutor.AndroidUseCaseExecution
import vn.minerva.core.base.domain.interactor.ResultListener
import vn.minerva.core.base.domain.interactor.UseCaseTask
import vn.minerva.travinh.app.presentation.navigater.AndroidScreenNavigator
import vn.minerva.travinh.func.main.domain.MainConfigUseCase

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