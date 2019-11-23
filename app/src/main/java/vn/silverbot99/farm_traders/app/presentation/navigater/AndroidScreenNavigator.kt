package vn.silverbot99.farm_traders.app.presentation.navigater

import android.content.Intent
import be.trikke.intentbuilder.Flow
import com.rabbitmq.client.AMQP
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.app.presentation.navigation.ScreenNavigator
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel

class AndroidScreenNavigator constructor(val mvpActivity: MvpActivity) : ScreenNavigator {
    override fun gotoFarmDetailScreen(farmItemModel: LocationFarmItemModel/*farmId: String*/) {
        Flow.gotoFarmDetailActivity(farmItemModel)
            .launch(mvpActivity)
    }

    override fun gotoProductList(categoryId: String) {
        Flow.gotoProductListActivity(categoryId)
            .launch(mvpActivity)
    }

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


    override fun callToDriver(phoneDriver: String) {
    }

    override fun sendSMSToDriver(phoneDriver: String) {
    }

}