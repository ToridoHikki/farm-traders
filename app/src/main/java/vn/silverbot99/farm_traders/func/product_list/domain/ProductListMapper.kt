package vn.silverbot99.farm_traders.func.product_list.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.string.getValueOrDefaultIsEmpty
import vn.silverbot99.core.base.domain.mapper.Mapper
import vn.silverbot99.farm_traders.app.data.network.response.ProductListResponse
import vn.silverbot99.farm_traders.func.product_list.presentation.model.ProductListItemModel

class ProductListMapper : Mapper<ProductListResponse, MutableList<ViewModel>> {
    override fun map(input: ProductListResponse): MutableList<ViewModel> {
        val list: MutableList<ViewModel> = mutableListOf<ViewModel>()
        input.productList.map{
            list.add(
                ProductListItemModel(
                    name = it.name.getValueOrDefaultIsEmpty(),
                    photo = it.photo.getValueOrDefaultIsEmpty(),
                    productId = it.productId.getValueOrDefaultIsEmpty(),
                    price = it.price.getValueOrDefaultIsEmpty(),
                    description = it.description.getValueOrDefaultIsEmpty(),
                    categoryId = it.categoryId.getValueOrDefaultIsEmpty(),
                    farmId = it.farmId.getValueOrDefaultIsEmpty()
                )
            )
        }
        return list
    }

}