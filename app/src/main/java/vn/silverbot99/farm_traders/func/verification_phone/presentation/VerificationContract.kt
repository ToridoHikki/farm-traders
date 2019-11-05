package vn.silverbot99.farm_traders.func.verification_phone.presentation

import vn.silverbot99.core.base.presentation.mvp.base.MvpPresenter
import vn.silverbot99.core.base.presentation.mvp.base.MvpView

interface VerificationContract {
    interface View : MvpView {

        fun showLoading()

        fun hideLoading()

        fun showToast(message: String)

        fun getCode(code: String)

        fun verificateSuccess()

        fun showError(errorMsg: String)


    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun gotoMainActivity()
        abstract fun sendVerificationCode(phone: String)
        abstract fun verifyCode(code: String)
    }
}