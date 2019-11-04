package vn.silverbot99.farm_traders.func.splash

import be.trikke.intentbuilder.BuildIntent
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.func.splash.presentation.SplashView

@BuildIntent
class SplashActivity : MvpActivity() {

    override fun createAndroidMvpView(): AndroidMvpView {
        return SplashView(this, SplashView.ViewCreator(this, null))
    }
}