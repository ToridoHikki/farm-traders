package vn.silverbot99.farm_traders.func.sign_up.presentation

import vn.silverbot99.core.base.presentation.mvp.base.MvpPresenter
import vn.silverbot99.core.base.presentation.mvp.base.MvpView
import vn.silverbot99.farm_traders.func.sign_up.presentation.model.UserFirebaseModel


interface SignUpContract {
        //anh coi cấu trúc ở đây. view chỉ show và xử lý các hành động là do thằng prensenter.
    interface View : MvpView {
        fun signUpSuccess(phone: String)

        fun showToast(message: String)

        fun showLoading()

        fun hideLoading()

        fun showError(errorMsg: String)


    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun createAccount(userFirebaseModel: UserFirebaseModel)
        abstract fun gotoAuthencationPhoneView(phone:String)
    }
}