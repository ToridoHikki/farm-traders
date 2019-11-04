@file:Suppress("DEPRECATION")

package vn.silverbot99.core.app.util

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.support.design.widget.TextInputLayout
import android.telephony.TelephonyManager
import android.text.Editable
import kotlinex.number.getValueOrDefaultIsZero
import kotlinex.string.getValueOrDefaultIsEmpty
import vn.silverbot99.core.app.common.AppConfigs
import java.security.MessageDigest
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*


class Utils {
    companion object {
        @JvmStatic
        fun getVersionCode(context: Context): Int {
            var versionCode = 0
            try {
                val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
                versionCode = pInfo.versionCode
                return versionCode
            } catch (ex: Exception) {
            }

            return versionCode
        }

        @JvmStatic
        fun getVersionName(context: Context): String {
            var versionName = ""
            try {
                val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
                versionName = pInfo.versionName
                return versionName
            } catch (e: Exception) {
            }

            return versionName
        }

        @JvmStatic
        fun setupInput(input: TextInputLayout) {
            input.setOnKeyListener { _, _, _ ->
                if (isNotEmpty(input.editText!!.text)) {
                    input.error = null
                }
                false
            }
        }

        @JvmStatic
        private fun isNotEmpty(text: Editable?): Boolean {
            return text != null && text.isNotEmpty()
        }

        @JvmStatic
        fun convertDateFormat(inputDate: String, fromFormat: SimpleDateFormat, toFormat: SimpleDateFormat): String {
            if (inputDate.isEmpty()) return ""
            return try {
                val toDate = fromFormat.parse(inputDate)
                toFormat.format(toDate)
            } catch (ex: java.lang.Exception) {
                ""
            }
        }

        @JvmStatic
        fun formatMoney(value: Double): String {
            val myFormatter = DecimalFormat("###,###,###", DecimalFormatSymbols(Locale.ITALIAN))
            return myFormatter.format(value)
        }
        @JvmStatic
        fun formatMoneyVND(value: Double): String {
            val myFormatter = DecimalFormat("###,###,###", DecimalFormatSymbols(Locale.ITALIAN))
            return myFormatter.format(value).plus(" đ")
        }
        @JvmStatic
        fun formatPercent(value: Double): String {
            val myFormatter = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ITALIAN))
            return myFormatter.format(value)
        }

        @JvmStatic
        fun getCoverDateRequest(date: String): String {
            return try {
                val simpleDateFormat = SimpleDateFormat(DateTimeFormat.DDMMYYYY.format, Locale("vi", "VI"))
                val dateTime = simpleDateFormat.parse(date)
                val dateFormat = SimpleDateFormat(DateTimeFormat.YYYY_MM_DD.format, Locale("vi", "VI"))
                dateFormat.format(dateTime)
            } catch (ex: Exception) {
                ""
            }
        }

        @JvmStatic
        fun getCoverDateRequest(date: Calendar): String {
            val dateFormat = SimpleDateFormat(DateTimeFormat.YYYY_MM_DD.format, Locale("vi", "VI"))
            return dateFormat.format(date.time)
        }

        @JvmStatic
        fun getCoverDateShow(date: String): String {
            return try {
                val simpleDateFormat = SimpleDateFormat(DateTimeFormat.YYYY_MM_DD.format, Locale("vi", "VI"))
                val dateTime = simpleDateFormat.parse(date)
                val dateFormat = SimpleDateFormat(DateTimeFormat.DDMMYYYY.format, Locale("vi", "VI"))
                dateFormat.format(dateTime)
            } catch (ex: Exception) {
                ""
            }
        }

        @JvmStatic
        fun getCoverDateShow(date: Calendar): String {
            val dateFormat = SimpleDateFormat(DateTimeFormat.DDMMYYYY.format, Locale("vi", "VI"))
            return dateFormat.format(date.time)
        }

        @JvmStatic
        fun getStringFromCalendar(date: Calendar, format: String): String {
            return try {
                SimpleDateFormat(format, Locale("vi", "VI")).format(date.time)
            } catch (ex: Exception) {
                ""
            }
        }

        @JvmStatic
        fun coverToCalendar(date: String, format: String): Calendar? {
            return try {
                val simpleDateFormat = SimpleDateFormat(format, Locale("vi", "VI"))
                val cal = Calendar.getInstance()
                cal.time = simpleDateFormat.parse(date)
                cal
            } catch (ex: Exception) {
                null
            }
        }

        fun md5(input: String): String {
            val md = MessageDigest.getInstance("MD5")
            val digested = md.digest(input.toByteArray())
            return digested.joinToString("") {
                String.format("%02x", it)
            }
        }


        @SuppressLint("MissingPermission", "HardwareIds")
        fun getNumberPhone(): String {
            return try {
                val tMgr = AppConfigs.instance.getBaseApplication().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                tMgr.line1Number.getValueOrDefaultIsEmpty()
            } catch (ex: java.lang.Exception) {
                ""
            }
        }

        fun getBuildVersion(): String {
            return Build.VERSION.RELEASE
        }

        fun getPlatformType(): String {
            return "android"
        }

        fun getPlatformVersion(): String {
            return Build.VERSION.SDK_INT.getValueOrDefaultIsZero().toString()
        }

    }

}