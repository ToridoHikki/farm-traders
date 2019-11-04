package vn.minerva.travinh.func.gasoline_store_detail.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class GasolineStoreDetailViewModel(
    var companyId: Int,
    var companyType: String,
    var companyName: String,
    var companyOwner: String,
    var companyMobile: String,
    var companyAddress: String,
    var storeId: Int,
    var storeName: String,
    var storeAddress: String,
    var storeDistrictId: Int,
    var storeDistrictName: String,
    var storeWardId: Int,
    var storeWardName: String,
    var storeLon: Double,
    var storeLap: Double,
    var storeOwner: String,
    var storeMobile: String,

    var imageList: List<GasolineStoreDetailImageListViewModel>,
    var nextAccreditationDate: String,
    var tankList: List<String>,
    var documentList: List<String> ): ViewModel


