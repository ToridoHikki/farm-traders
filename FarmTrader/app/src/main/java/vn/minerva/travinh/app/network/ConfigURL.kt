package vn.minerva.travinh.app.network

import vn.minerva.travinh.BuildConfig


class ConfigURL {
    companion object {
        private const val API_COOP_PRODUCT = "http://gis.trungtamtdctravinh.vn/api/"
        private const val API_COOP_UAT = "http://gis.trungtamtdctravinh.vn/api/"
        @JvmStatic
        fun getApiUrl(): String {
            return when {
                BuildConfig.USE_DATA_PRODUCT -> {
                    API_COOP_PRODUCT
                }
                else -> {
                    API_COOP_UAT
                }
            }
        }
    }
}