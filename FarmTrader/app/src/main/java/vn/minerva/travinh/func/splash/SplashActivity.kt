package vn.minerva.travinh.func.splash

import be.trikke.intentbuilder.BuildIntent
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.travinh.func.splash.presentation.SplashView

@BuildIntent
class SplashActivity : MvpActivity() {

    override fun createAndroidMvpView(): AndroidMvpView {
        return SplashView(this, SplashView.ViewCreator(this, null))
    }
}