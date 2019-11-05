package vn.silverbot99.farm_traders.func.sign_up

import be.trikke.intentbuilder.BuildIntent
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.func.sign_up.presentation.SignUpView

@BuildIntent
class SignUpActivity : MvpActivity() {
    override fun createAndroidMvpView(): AndroidMvpView {
        return SignUpView(this,SignUpView.ViewCreator(this,null))
    }
}