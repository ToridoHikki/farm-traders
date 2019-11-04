package vn.minerva.travinh.app.network.header

import com.orhanobut.logger.Logger
import vn.minerva.core.app.util.TOTP
import vn.minerva.travinh.BuildConfig
import vn.minerva.travinh.app.config.ConfigUtil
import java.text.SimpleDateFormat
import java.util.*


class HeaderParamUtils {
    companion object {
        @JvmStatic
        fun getListHeader(method: String, url: String, requestBody: String?): Map<String, String> {
//            Log.d("HeaderParamUtils", requestBody + "")
            val headers = mutableMapOf<String, String>()
            headers[HeaderConst.DEVICE] = getAccessDevice()
            val isFirstLogin = ConfigUtil.isFirstLoginApp

//            Log.d("HeaderParamUtils", isFirstLogin.toString())
            if (isFirstLogin) {
                val passport = ConfigUtil.passport
                passport?.let { useModel ->
                    val userToken = useModel.key
                    if (userToken.isNotEmpty()) {
                        headers[HeaderConst.AUTHOR] = "Bearer $userToken"
                    }
                }
            }
//            headers[HeaderConst.ACCEPT] = HeaderConst.ACCEPT_VALUE
            headers[HeaderConst.X_CONTENT_TYPE] = HeaderConst.X_CONTENT_TYPE_VALUE
            return headers
        }

        private fun getAccessDevice(): String {
            val temp: String
            val calendar = Calendar.getInstance()
            val second = calendar.get(Calendar.SECOND)
            val sdf = SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault())
            temp = sdf.format(calendar.time)
            var timeNoSecond = calendar.timeInMillis / 1000
            try {
                val d = sdf.parse(temp)
                timeNoSecond = d.time / 1000
            } catch (ignored: Exception) {
            }

            if (second < 30) {
                timeNoSecond += 29
            } else {
                timeNoSecond += 59
            }

            try {
                val secretKey = when {
                    BuildConfig.USE_DATA_PRODUCT -> HeaderConst.SECRET_KEY_PRODUCT
                    else -> HeaderConst.SECRET_KEY_UAT
                }
                val timeTest = "" + timeNoSecond
                return TOTP.generateTOTP(secretKey, timeTest, "6")
            } catch (e: Exception) {
                Logger.e(e.message)
            }

            return ""
        }
    }
}