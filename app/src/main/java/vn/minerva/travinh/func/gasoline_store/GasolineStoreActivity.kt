package vn.minerva.travinh.func.gasoline_store

import be.trikke.intentbuilder.BuildIntent
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.travinh.func.gasoline_store.presentation.GasolineStoreView

@BuildIntent
class GasolineStoreActivity : MvpActivity(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return GasolineStoreView(this,GasolineStoreView.ViewCreator(this,null))
    }
}