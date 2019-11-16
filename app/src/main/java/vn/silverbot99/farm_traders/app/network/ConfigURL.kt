package vn.silverbot99.farm_traders.app.network

import vn.silverbot99.farm_traders.BuildConfig


class ConfigURL {
    companion object {
        private const val API_COOP_PRODUCT = "https://us-central1-farm-trader.cloudfunctions.net/"
        private const val API_COOP_UAT = "https://us-central1-farm-trader.cloudfunctions.net/"
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