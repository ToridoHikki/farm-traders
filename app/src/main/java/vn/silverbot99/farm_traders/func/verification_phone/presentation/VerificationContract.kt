package vn.silverbot99.farm_traders.func.verification_phone.presentation

import vn.silverbot99.core.base.presentation.mvp.base.MvpPresenter
import vn.silverbot99.core.base.presentation.mvp.base.MvpView

interface VerificationContract {
    interface View : MvpView {

        fun showLoading()

        fun hideLoading()

        fun showError(errorMsg: String)


    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun gotoMainView()
        abstract fun verification(phone: String)
    }
}