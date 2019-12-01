package vn.silverbot99.farm_traders.func.farm_detail.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel


class FarmDetailProductListItemModel(
    var productId: String = "",
    var name: String = "",
    var price: String = "",
    var description: String = "",
    var photo: String = "",
    var farmId: String = "",
    var categoryId: String = ""
): ViewModel