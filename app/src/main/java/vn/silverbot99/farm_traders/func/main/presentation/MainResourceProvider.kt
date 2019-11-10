package vn.silverbot99.farm_traders.func.main.presentation

import android.graphics.drawable.Drawable
import vn.silverbot99.core.base.domain.provider.AndroidResourceProvider
import vn.silverbot99.farm_traders.R

class MainResourceProvider : AndroidResourceProvider() {
   fun getLogoutMessageDialog():String{
       return resourceManager.getString(R.string.text_dialog_logout)
   }

    fun getDefaultImageViewHeight():Int{
        return resourceManager.getDimensionPixelSize(R.dimen.nav_header_height)
    }
    fun getIconMap(): Int{
        return R.drawable.mapbox_compass_icon
    }
    fun getHomeTitle(): String{
        return resourceManager.getString(R.string.tab_price_products_today)

    }
    fun getFarmNearestTitle(): String{
        return resourceManager.getString(R.string.tab_map)

    }
    fun getListProducts(): String{
        return resourceManager.getString(R.string.tab_products)

    }
}