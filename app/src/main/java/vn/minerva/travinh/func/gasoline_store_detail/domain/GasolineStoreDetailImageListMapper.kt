package vn.minerva.travinh.func.gasoline_store_detail.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.number.getValueOrDefaultIsZero
import kotlinex.string.getValueOrDefaultIsEmpty
import vn.minerva.core.base.domain.mapper.Mapper
import vn.minerva.travinh.app.data.network.response.GasolineStoreDetailResponse
import vn.minerva.travinh.func.gasoline_store_detail.presentation.model.GasolineStoreDetailImageListViewModel

class GasolineStoreDetailImageListMapper : Mapper<GasolineStoreDetailResponse, MutableList<ViewModel>> {
    override fun map(input: GasolineStoreDetailResponse): MutableList<ViewModel> {
        val list: MutableList<ViewModel> = mutableListOf<ViewModel>()
        input.imageList.map{
            list.add(
                GasolineStoreDetailImageListViewModel(
                    imageType = it.imageType.getValueOrDefaultIsEmpty(),
                    imageUrl = it.imageUrl.getValueOrDefaultIsEmpty()
                )
            )
        }
        return list
    }
}