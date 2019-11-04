package vn.minerva.travinh.func.location_gasoline_medical.presentation.renderer

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout_gasoline_store_detail_vholder.view.*
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.core.base.presentation.mvp.android.model.ViewRenderer
import vn.minerva.travinh.R
import vn.minerva.travinh.func.location_gasoline_medical.presentation.model.LocationGasolineItemModel

class LocationGasolineRender (mvpActivity: MvpActivity) : ViewRenderer<LocationGasolineItemModel>(mvpActivity) {
    override fun getLayoutId(): Int = R.layout.item_layout_gasoline_store_detail_vholder

    override fun getModelClass(): Class<LocationGasolineItemModel> = LocationGasolineItemModel::class.java

    override fun bindView(model: LocationGasolineItemModel, viewRoot: View) {
        Glide.with(context).load(model.storeThumbnail).into(viewRoot.ivGasolineStore)
        viewRoot.tvGasStoreName.text = model.storeName
        viewRoot.tvGasStoreAddress.text = model.storeAddress
        if(model.storeMobile.equals(null)||model.storeMobile.equals("")){
            viewRoot.tvGasStorePhone.visibility = View.GONE
        }
        else{
            viewRoot.tvGasStorePhone.text = model.storeMobile
        }
        viewRoot.tvGasStoreDate.text = model.nextAccreditationDate
    }
}