package vn.minerva.travinh.func.medical_detail.presentation.contact

import vn.minerva.core.app.domain.excecutor.AndroidUseCaseExecution
import vn.minerva.core.base.domain.interactor.ResultListener
import vn.minerva.core.base.domain.interactor.UseCaseTask
import vn.minerva.travinh.app.data.network.request.MedicalDetailRequest
import vn.minerva.travinh.func.medical_detail.domain.MedicalDetailRootUseCase

class MedicalDetailContactPresenter : MedicalDetailContactContract.Presenter(){
    private var useCaseTask: UseCaseTask? = null
    private var medicalDetailRootUseCase = MedicalDetailRootUseCase(AndroidUseCaseExecution())
    override fun getDataMedicalContact(id: Int) {
        val requestBody = MedicalDetailRequest(
            id = id
        )

        useCaseTask?.cancel()
        useCaseTask = medicalDetailRootUseCase.executeAsync(object :
            ResultListener<MedicalDetailRootUseCase.Output> {
            override fun success(data: MedicalDetailRootUseCase.Output) {
                if (data.medicalDetailResponse.success) {
                    view?.showContactInfo(data.medicalDetailResponse)
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
            MedicalDetailRootUseCase.Input(requestBody))
    }
}