package vn.minerva.travinh.app.presentation.navigation

import vn.minerva.travinh.app.common.AppConstants

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