package vn.silverbot99.farm_traders.app.presentation.navigation

import vn.silverbot99.farm_traders.app.common.AppConstants
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel

interface ScreenNavigator {
    fun gotoMainActivity()
    fun gotoPassportActivity(forceCode: Int = AppConstants.FORCE_LOGIN_DEFAULT)
    fun callToDriver(phoneDriver: String)
    fun sendSMSToDriver(phoneDriver: String)
    fun gotoImageLibrary()
    fun gotoVerificationPhone(phone: String)
    fun gotoSignUpScreen()
    fun gotoProductList(categoryId: String)
    fun gotoFarmDetailScreen(farmItemModel: LocationFarmItemModel/*farmId: String*/)


}