package vn.silverbot99.farm_traders.func.passport.presentation

import vn.silverbot99.core.base.presentation.mvp.base.MvpPresenter
import vn.silverbot99.core.base.presentation.mvp.base.MvpView
import vn.silverbot99.farm_traders.app.data.network.request.PassportRequest
import vn.silverbot99.farm_traders.app.data.network.response.PassportResponse
import vn.silverbot99.farm_traders.func.passport.presentation.model.UserItemModel


interface PassportContract {
        //anh coi cấu trúc ở đây. view chỉ show và xử lý các hành động là do thằng prensenter.
    interface View : MvpView {
        fun showLoading()

        fun hideLoading()

//        fun handleAfterLogin(data: PassportResponse)
        fun loginSuccessful()
        fun showError(errorMsg: String)
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun login(userItemModel: UserItemModel)
        abstract fun gotoMainActivity()
        abstract fun gotoLogUpView()
    }
}