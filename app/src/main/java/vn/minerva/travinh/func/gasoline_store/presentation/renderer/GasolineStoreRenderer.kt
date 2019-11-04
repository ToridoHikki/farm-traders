package vn.minerva.travinh.func.gasoline_store.presentation.renderer

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout_gasoline_store_detail_vholder.view.*
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.core.base.presentation.mvp.android.model.ViewRenderer
import vn.minerva.travinh.R
import vn.minerva.travinh.func.gasoline_store.presentation.model.GasolineViewModel

class GasolineStoreRenderer(mvpActivity: MvpActivity) : ViewRenderer<GasolineViewModel>(mvpActivity){
    override fun getLayoutId(): Int = R.layout.item_layout_gasoline_store_detail_vholder

    override fun getModelClass(): Class<GasolineViewModel> = GasolineViewModel::class.java

    override fun bindView(model: GasolineViewModel, viewRoot: View) {
        Glide.with(context).load(model.storeThumbnail).into(viewRoot.ivGasolineStore)
        viewRoot.tvGasStoreName.text = model.storeName
        viewRoot.tvGasStoreAddress.text = model.storeAddress
        viewRoot.tvGasStorePhone.text = model.storeAddress
        viewRoot.tvGasStorePhone.text = model.storeMobile
        viewRoot.tvGasStoreDate.text = model.nextAccreditationDate

    }

}