package vn.minerva.travinh.func.medical.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.number.getValueOrDefaultIsZero
import kotlinex.string.getValueOrDefaultIsEmpty
import vn.silverbot99.core.base.domain.mapper.Mapper
import vn.silverbot99.farm_traders.app.data.network.response.CategoriesResponse
import vn.silverbot99.farm_traders.func.category.presentation.model.CategoryItemModel

class CategoryMapper : Mapper<CategoriesResponse, MutableList<ViewModel>> {
    override fun map(input: CategoriesResponse): MutableList<ViewModel> {
        val list: MutableList<ViewModel> = mutableListOf<ViewModel>()
        input.categoriesList.map{
            list.add(CategoryItemModel(
                name = it.name.getValueOrDefaultIsEmpty(),
                photo = it.name.getValueOrDefaultIsEmpty(),
                categoryId = it.categoryId.getValueOrDefaultIsEmpty()
            ))
        }
        return list
    }

}