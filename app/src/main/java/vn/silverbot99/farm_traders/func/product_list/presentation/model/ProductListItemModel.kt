package vn.silverbot99.farm_traders.func.product_list.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import java.io.Serializable


class ProductListItemModel(
    var productId: String = "",
    var name: String = "",
    var price: String = "",
    var description: String = "",
    var photo: String = "",
    var farmId: String = "",
    var categoryId: String = ""
    ): ViewModel, Serializable