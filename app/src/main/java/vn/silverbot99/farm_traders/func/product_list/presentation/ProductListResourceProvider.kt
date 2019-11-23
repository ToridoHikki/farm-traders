package vn.silverbot99.farm_traders.func.product_list.presentation

import vn.silverbot99.core.base.domain.provider.AndroidResourceProvider
import vn.silverbot99.farm_traders.R

class ProductListResourceProvider  : AndroidResourceProvider(){
    fun getTitle(): String{
        return resourceManager.getString(R.string.product_list)
    }
}