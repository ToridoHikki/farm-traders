package vn.minerva.travinh.func.passport.presentation

import vn.minerva.core.base.presentation.mvp.base.MvpPresenter
import vn.minerva.core.base.presentation.mvp.base.MvpView
import vn.minerva.travinh.app.data.network.request.PassportRequest
import vn.minerva.travinh.app.data.network.response.PassportResponse


interface PassportContract {
        //anh coi cấu trúc ở đây. view chỉ show và xử lý các hành động là do thằng prensenter.
    interface View : MvpView {
        fun showLoading()

        fun hideLoading()

        fun handleAfterLogin(data: PassportResponse)

        fun showError(errorMsg: String)
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun login(passportRequest: PassportRequest)
        abstract fun gotoMainActivity()
    }
}