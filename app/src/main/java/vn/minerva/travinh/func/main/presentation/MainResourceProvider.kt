package vn.minerva.travinh.func.main.presentation

import vn.minerva.core.base.domain.provider.AndroidResourceProvider
import vn.minerva.travinh.R

class MainResourceProvider : AndroidResourceProvider() {
   fun getLogoutMessageDialog():String{
       return resourceManager.getString(R.string.text_dialog_logout)
   }

    fun getDefaultImageViewHeight():Int{
        return resourceManager.getDimensionPixelSize(R.dimen.nav_header_height)
    }
}