package vn.silverbot99.farm_traders.app.presentation.navigation

import vn.silverbot99.farm_traders.app.common.AppConstants

interface ScreenNavigator {
    fun gotoMainActivity()
    fun gotoPassportActivity(forceCode: Int = AppConstants.FORCE_LOGIN_DEFAULT)
    fun goToProfileActivity()
    fun callToDriver(phoneDriver: String)
    fun sendSMSToDriver(phoneDriver: String)
    fun gotoGasolineStoreActivity()
    fun gotoMedicalActivity()
    fun gotoGasolineStoreDetailActivity(id: Int)
    fun gotoImageLibrary()
    fun gotoMedicalDetailActivity(id: Int)
    fun gotoNewsActivity()
}