package vn.silverbot99.farm_traders.app.presentation.navigater

import android.content.Intent
import be.trikke.intentbuilder.Flow
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.app.presentation.navigation.ScreenNavigator

class AndroidScreenNavigator constructor(val mvpActivity: MvpActivity) : ScreenNavigator {
    override fun gotoVerificationPhone(phone: String) {
        Flow.gotoVerificationActivity(phone)
            .launch(mvpActivity)
    }

    override fun gotoSignUpScreen() {
        Flow.gotoSignUpActivity()
            .launch(mvpActivity)
    }

    override fun gotoImageLibrary() {
        val intent = Intent()
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mvpActivity.startActivity(intent)
    }

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