package vn.silverbot99.farm_traders.app.presentation.navigater

import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import be.trikke.intentbuilder.Flow
//import com.rabbitmq.client.AMQP
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.app.presentation.navigation.ScreenNavigator
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel
import vn.silverbot99.farm_traders.func.product_list.presentation.model.ProductListItemModel
import com.mapbox.mapboxsdk.style.expressions.Expression.number
import android.support.v4.content.ContextCompat.startActivity



class AndroidScreenNavigator constructor(val mvpActivity: MvpActivity) : ScreenNavigator {
    override fun gotoMapScreen(locationFarmItemModel: LocationFarmItemModel) {
        Flow.gotoMapActivity(locationFarmItemModel)
            .launch(mvpActivity)
    }

    override fun gotoProductDetailScreen(productItemModel: ProductListItemModel) {
        Flow.gotoProductDetailActivity(productItemModel)
            .launch(mvpActivity)
    }

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


    override fun callToFarmer(phoneFarmer: String) {
       // val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneFarmer"))
        // val intent = Intent(Intent.ACTION_CALL, Uri.fromParts("sms", phoneFarmer, null))
        //intent.data = Uri.parse("tel:$number")
        mvpActivity.startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneFarmer")))
    }

    override fun sendSMSToFarmer(phoneFarmer: String) {
        mvpActivity.startActivity(Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", phoneFarmer, null)))

    }

}