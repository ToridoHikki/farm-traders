package vn.minerva.travinh.func.gasoline_store_detail.presentation.contact

import vn.minerva.core.app.domain.excecutor.AndroidUseCaseExecution
import vn.minerva.core.base.domain.interactor.ResultListener
import vn.minerva.core.base.domain.interactor.UseCaseTask
import vn.minerva.travinh.app.data.network.request.GasolineStoreDetailRequest
import vn.minerva.travinh.func.gasoline_store_detail.domain.GasolineStoreDetailRootUseCase

class GasolineStoreDetailContactPresenter :GasolineStoreDetailContactContract.Presenter() {
    private var useCaseTask: UseCaseTask? = null
    private var gasolineStoreDetailRootUseCase = GasolineStoreDetailRootUseCase(AndroidUseCaseExecution())
    override fun getDataGasolineStoreContact(id: Int) {
        val requestBody = GasolineStoreDetailRequest(
            id = id
        )

        useCaseTask?.cancel()
        useCaseTask = gasolineStoreDetailRootUseCase.executeAsync(object : ResultListener<GasolineStoreDetailRootUseCase.Output> {
            override fun success(data: GasolineStoreDetailRootUseCase.Output) {
                if (data.gasolineStoreDetailResponse.success) {
                    view?.showContactInfo(data.gasolineStoreDetailResponse)
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
            GasolineStoreDetailRootUseCase.Input(requestBody))
    }

}