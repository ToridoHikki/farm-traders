package vn.silverbot99.farm_traders.app.network

import vn.silverbot99.farm_traders.BuildConfig


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