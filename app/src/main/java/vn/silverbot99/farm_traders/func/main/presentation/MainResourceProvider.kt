package vn.silverbot99.farm_traders.func.main.presentation

import vn.silverbot99.core.base.domain.provider.AndroidResourceProvider
import vn.silverbot99.farm_traders.R

class MainResourceProvider : AndroidResourceProvider() {
   fun getLogoutMessageDialog():String{
       return resourceManager.getString(R.string.text_dialog_logout)
   }

    fun getDefaultImageViewHeight():Int{
        return resourceManager.getDimensionPixelSize(R.dimen.nav_header_height)
    }


}