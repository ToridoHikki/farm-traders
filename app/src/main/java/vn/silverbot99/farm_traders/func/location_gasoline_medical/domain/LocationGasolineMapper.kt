package vn.silverbot99.farm_traders.func.location_gasoline_medical.domain

import android.util.Log
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.number.getValueOrDefaultIsZero
import kotlinex.string.getValueOrDefaultIsEmpty
import vn.silverbot99.core.base.domain.mapper.Mapper
import vn.silverbot99.farm_traders.app.data.network.response.GasolineStoreResponse
import vn.silverbot99.farm_traders.func.location_gasoline_medical.presentation.model.LocationGasolineItemModel

class LocationGasolineMapper : Mapper<GasolineStoreResponse, MutableList<ViewModel>> {
    override fun map(input: GasolineStoreResponse): MutableList<ViewModel> {
        val list: MutableList<ViewModel> = mutableListOf<ViewModel>()
        input.storeList.map{
            list.add(
                LocationGasolineItemModel(
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

                )
            )
        }
        Log.i("LocationData", "Map data GasolineStoreResponse success")
        return list
    }

}