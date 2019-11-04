package vn.minerva.travinh.func.passport.presentation

import kotlinex.string.getValueOrDefaultIsEmpty
import vn.minerva.core.app.domain.excecutor.AndroidUseCaseExecution
import vn.minerva.core.base.domain.interactor.ResultListener
import vn.minerva.core.base.domain.interactor.UseCaseTask
import vn.minerva.travinh.app.data.network.request.PassportRequest
import vn.minerva.travinh.app.data.network.response.PassportResponse
import vn.minerva.travinh.app.presentation.navigation.ScreenNavigator
import vn.minerva.travinh.func.passport.domain.LoginUseCase

class PassportPresenter(private val screenNavigator: ScreenNavigator) : PassportContract.Presenter() {
    private var loginUseCase = LoginUseCase(AndroidUseCaseExecution())
    private var loginUseCaseTask: UseCaseTask? = null

    override fun gotoMainActivity() {
        screenNavigator.gotoMainActivity()
    }

    override fun login(passportRequest: PassportRequest) {
        view?.showLoading()
        loginUseCaseTask?.cancel()
        loginUseCaseTask = loginUseCase.executeAsync(object : ResultListener<PassportResponse> {
            override fun done() {
            }

            override fun fail(errorCode: Int, msgError: String) {
                view?.showError(msgError)
            }

            override fun success(data: PassportResponse) {
                view?.hideLoading()
                if (data.success) {
                    view?.handleAfterLogin(data)
                } else {
                    view?.showError(data.detail.getValueOrDefaultIsEmpty())
                }
            }
        }, passportRequest)
    }
}