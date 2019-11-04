package vn.minerva.travinh.func.main.presentation

import android.net.Uri
import vn.minerva.core.base.domain.provider.AndroidResourceProvider
import vn.minerva.travinh.R
import vn.minerva.travinh.app.config.ConfigUtil

class MainResourceProvider : AndroidResourceProvider() {
   fun getLogoutMessageDialog():String{
       return resourceManager.getString(R.string.text_dialog_logout)
   }

    fun getDefaultImageViewHeight():Int{
        return resourceManager.getDimensionPixelSize(R.dimen.nav_header_height)
    }


}