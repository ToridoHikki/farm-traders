package vn.minerva.travinh.func.gasoline_store.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel


class GasolineViewModel (
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
    var storeThumbnail: String,
    var nextAccreditationDate: String
): ViewModel