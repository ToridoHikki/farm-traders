package vn.minerva.travinh.func.medical.presentation

import vn.minerva.core.app.domain.excecutor.AndroidUseCaseExecution
import vn.minerva.core.base.domain.interactor.ResultListener
import vn.minerva.core.base.domain.interactor.UseCaseTask
import vn.minerva.travinh.app.data.network.request.MedicalRequest
import vn.minerva.travinh.app.presentation.navigation.ScreenNavigator
import vn.minerva.travinh.func.medical.domain.MedicalMapper
import vn.minerva.travinh.func.medical.domain.MedicalRootUseCase

class MedicalPresenter(var screenNavigator: ScreenNavigator): MedicalContract.Presenter() {
    override fun goMedicalDetail(id: Int) {
        screenNavigator.gotoMedicalDetailActivity(id)
    }

    override fun gobackMainView() {
        screenNavigator.gotoMainActivity()
    }

    private var useCaseTask: UseCaseTask? = null
    private var medicalDetailRootUseCase = MedicalRootUseCase(AndroidUseCaseExecution())

    override fun getMedicalList(/*latitude: Long, longitude: Long,*/ page: Int) {
        if (page == 1) {
            view?.showLoading()
        }

        val requestBody = MedicalRequest(
            page = page/*,
            latitude = 9.947213700426019,
            longitude = 106.33966710146512*/
        )

        useCaseTask?.cancel()
        useCaseTask = medicalDetailRootUseCase.executeAsync(object : ResultListener<MedicalRootUseCase.Output> {
            override fun success(data: MedicalRootUseCase.Output) {
                if (data.medicalResponse.success) {
                    view?.showMedicalDetail(MedicalMapper().map(data.medicalResponse),data.medicalResponse.total)
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
            MedicalRootUseCase.Input(requestBody))
    }


}