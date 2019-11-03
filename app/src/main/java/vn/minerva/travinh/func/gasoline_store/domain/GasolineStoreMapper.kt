package vn.minerva.travinh.func.gasoline_store.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.number.getValueOrDefaultIsZero
import kotlinex.string.getValueOrDefaultIsEmpty
import vn.minerva.core.base.domain.mapper.Mapper
import vn.minerva.travinh.app.data.network.response.GasolineStoreResponse
import vn.minerva.travinh.func.gasoline_store.presentation.model.GasolineViewModel

class GasolineStoreMapper :Mapper<GasolineStoreResponse,MutableList<ViewModel>>{
    override fun map(input: GasolineStoreResponse): MutableList<ViewModel> {
        val list: MutableList<ViewModel> = mutableListOf<ViewModel>()
        input.storeList.map{
            list.add(GasolineViewModel(
                companyId = it.companyId.getValueOrDefaultIsZero(),
                companyType = it.companyType.getValueOrDefaultIsEmpty(),
                companyName = it.companyName.getValueOrDefaultIsEmpty(),
                companyOwner = it.companyOwner.getValueOrDefaultIsEmpty(),
                companyMobile = it.companyMobile.getValueOrDefaultIsEmpty(),
                companyAddress = it.companyAddress.getValueOrDefaultIsEmpty(),
                storeId = it.storeId.getValueOrDefaultIsZero(),
                storeName = it.storeName.getValueOrDefaultIsEmpty(),
                storeAddress = it.storeAddress.getValueOrDefaultIsEmpty(),
                storeDistrictId = it.storeDistrictId.getValueOrDefaultIsZero(),
                storeDistrictName = it.storeDistrictName.getValueOrDefaultIsEmpty(),
                storeWardId = it.storeWardId.getValueOrDefaultIsZero(),
                storeWardName = it.storeWardName.getValueOrDefaultIsEmpty(),
                storeLon = it.storeLon.getValueOrDefaultIsZero(),
                storeLap = it.storeLap.getValueOrDefaultIsZero(),
                storeOwner = it.storeOwner.getValueOrDefaultIsEmpty(),
                storeMobile = it.storeMobile.getValueOrDefaultIsEmpty(),
                storeThumbnail = it.storethumbnail.getValueOrDefaultIsEmpty(),
                nextAccreditationDate = it.nextAccreditationDate.getValueOrDefaultIsEmpty()

            ))
        }
        return list
    }

}