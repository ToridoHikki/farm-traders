package vn.silverbot99.farm_traders.app.presentation.navigation

import vn.silverbot99.farm_traders.app.common.AppConstants
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel
import vn.silverbot99.farm_traders.func.product_list.presentation.model.ProductListItemModel

interface ScreenNavigator {
    fun gotoMainActivity()
    fun gotoPassportActivity(forceCode: Int = AppConstants.FORCE_LOGIN_DEFAULT)
    fun callToFarmer(phoneFarmer: String)
    fun sendSMSToFarmer(phoneFarmer: String)
    fun gotoImageLibrary()
    fun gotoVerificationPhone(phone: String)
    fun gotoSignUpScreen()
    fun gotoProductList(categoryId: String)
    fun gotoFarmDetailScreen(farmItemModel: LocationFarmItemModel/*farmId: String*/)
    fun gotoProductDetailScreen(productItemModel: ProductListItemModel)
    fun gotoMapScreen(locationFarmItemModel: LocationFarmItemModel)


}