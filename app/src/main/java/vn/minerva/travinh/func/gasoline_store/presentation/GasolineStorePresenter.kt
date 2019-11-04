package vn.minerva.travinh.func.gasoline_store.presentation

import vn.minerva.core.app.domain.excecutor.AndroidUseCaseExecution
import vn.minerva.core.base.domain.interactor.ResultListener
import vn.minerva.core.base.domain.interactor.UseCaseTask
import vn.minerva.travinh.app.data.network.request.GasolineStoreRequest
import vn.minerva.travinh.app.presentation.navigation.ScreenNavigator
import vn.minerva.travinh.func.gasoline_store.domain.GasolineStoreMapper
import vn.minerva.travinh.func.gasoline_store.domain.GasolineStoreRootUseCase


class GasolineStorePresenter(var screenNavigator: ScreenNavigator): GasolineStoreContract.Presenter(){
    override fun gobackMainView() {
        screenNavigator.gotoMainActivity()
    }

    override fun gotoGasStationDetail(id: Int) {
        screenNavigator.gotoGasolineStoreDetailActivity(id)
    }

    private var useCaseTask: UseCaseTask? = null
    private var gasolineStoreRootUseCase = GasolineStoreRootUseCase(AndroidUseCaseExecution())
    override fun getGasStationList(page: Int) {
        val requestBody = GasolineStoreRequest(
            page = page
        )

        useCaseTask?.cancel()
        useCaseTask = gasolineStoreRootUseCase.executeAsync(object : ResultListener<GasolineStoreRootUseCase.Output> {
            override fun success(data: GasolineStoreRootUseCase.Output) {
                if (data.gasolineStoreResponse.success) {
                    view?.showGasolineStoreDetail(GasolineStoreMapper().map(data.gasolineStoreResponse),data.gasolineStoreResponse.total)
                } else {
                    view?.showError("Error")
                }
            }

            override fun fail(errorCode: Int, msgError: String) {
                view?.showError("$errorCode - $msgError")
            }

            override fun done() {
                view?.hideLoading()
            }
            },
            GasolineStoreRootUseCase.Input(requestBody))
    }
}
