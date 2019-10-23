package vn.minerva.travinh.app.presentation.navigater

import android.content.Intent
import be.trikke.intentbuilder.Flow
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.travinh.app.presentation.navigation.ScreenNavigator

class AndroidScreenNavigator constructor(val mvpActivity: MvpActivity) : ScreenNavigator {
    override fun gotoMainActivity() {
        Flow.gotoMainActivity()
            .flags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            .launch(mvpActivity)
    }

    override fun gotoPassportActivity(forceCode: Int) {
        Flow.gotoPassportActivity(forceCode)
            .flags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            .launch(mvpActivity)
    }

    override fun goToProfileActivity() {
    }

    override fun callToDriver(phoneDriver: String) {
    }

    override fun sendSMSToDriver(phoneDriver: String) {
    }

}