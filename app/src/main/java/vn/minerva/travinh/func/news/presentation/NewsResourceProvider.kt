package vn.minerva.travinh.func.news.presentation

import vn.minerva.core.base.domain.provider.AndroidResourceProvider
import vn.minerva.travinh.R

class NewsResourceProvider:AndroidResourceProvider() {
    fun getNewsTitile():String{
        return resourceManager.getString(R.string.news)
    }
}