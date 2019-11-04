package vn.minerva.travinh.func.medical

import android.os.Bundle
import be.trikke.intentbuilder.BuildIntent
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.travinh.func.medical.presentation.MedicalView

@BuildIntent
class MedicalActivity : MvpActivity() {
    override fun createAndroidMvpView(): AndroidMvpView {
        return MedicalView(this, MedicalView.ViewCreator(this, null))
    }
}