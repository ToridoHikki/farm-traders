package vn.silverbot99.farm_traders.app.config

import kotlinex.boolean.getValueOrDefault
import vn.silverbot99.core.base.config.ConfigSaver
import vn.silverbot99.core.base.config.PaperConfigSaverImpl
import vn.silverbot99.farm_traders.app.data.network.request.PassportRequest
import vn.silverbot99.farm_traders.app.data.network.response.PassportResponse
import java.util.*


class ConfigUtil {
    companion object {
        val isShowDialogWarningLoggedOtherDevice: Boolean
            get() {
                val configSaver = PaperConfigSaverImpl(ConfigSaver.CONFIG_PAGER)
                val isShow =
                    configSaver.get<Boolean>(ConfigSaver.CONFIG_SETTING_SAVED_IS_SHOW_DIALOG_WARNING_LOGGED_OTHER_DEVICE)
                return isShow != null && isShow
            }

        val passport: PassportResponse?
            get() {
                val configSaver = PaperConfigSaverImpl(ConfigSaver.CONFIG_PAGER)
                return configSaver.get(ConfigSaver.CONFIG_SETTING_PASSPORT)
            }
        val passportRequest: PassportRequest?
            get() {
                val configSaver = PaperConfigSaverImpl(ConfigSaver.CONFIG_PAGER)
                return configSaver.get(ConfigSaver.CONFIG_SETTING_KEY_LOGIN)
            }
        val dateSelected: Calendar?
            get() {
                val configSaver = PaperConfigSaverImpl(ConfigSaver.CONFIG_PAGER)
                val dateLong = configSaver.get<Long>(ConfigSaver.CONFIG_SETTING_DATE_TIME)
                val calendar = Calendar.getInstance()
                if (dateLong != null)
                    calendar.timeInMillis = dateLong
                return calendar
            }
        val isFirstLoginApp: Boolean
            get() {
                val configSaver = PaperConfigSaverImpl(ConfigSaver.CONFIG_PAGER)
                val isFirstLoginApp = configSaver.get<Boolean>(ConfigSaver.CONFIG_SETTING_SAVED_IS_FIRST_LOGIN_APP)
                return isFirstLoginApp.getValueOrDefault()
            }

        @JvmStatic
        fun savePassport(passport: PassportResponse?) {
            val configSaver = PaperConfigSaverImpl(ConfigSaver.CONFIG_PAGER)
            configSaver.save(ConfigSaver.CONFIG_SETTING_PASSPORT, passport)
        }

        @JvmStatic
        fun saveIsFirstLoginApp(isFirstLoginApp: Boolean) {
            val configSaver = PaperConfigSaverImpl(ConfigSaver.CONFIG_PAGER)
            configSaver.save(ConfigSaver.CONFIG_SETTING_SAVED_IS_FIRST_LOGIN_APP, isFirstLoginApp)
        }

        @JvmStatic
        fun saveLoginRequest(request: PassportRequest) {
            val configSaver = PaperConfigSaverImpl(ConfigSaver.CONFIG_PAGER)
            return configSaver.save(ConfigSaver.CONFIG_SETTING_KEY_LOGIN, request)
        }

        @JvmStatic
        fun saveIsShowDialogWarningLoggedOtherDevice(isShow: Boolean) {
            val configSaver = PaperConfigSaverImpl(ConfigSaver.CONFIG_PAGER)
            configSaver.save(ConfigSaver.CONFIG_SETTING_SAVED_IS_SHOW_DIALOG_WARNING_LOGGED_OTHER_DEVICE, isShow)
        }

        @JvmStatic
        fun saveDateSelected(date: Calendar) {
            val configSaver = PaperConfigSaverImpl(ConfigSaver.CONFIG_PAGER)
            return configSaver.save(ConfigSaver.CONFIG_SETTING_DATE_TIME, date.timeInMillis)
        }


    }


}