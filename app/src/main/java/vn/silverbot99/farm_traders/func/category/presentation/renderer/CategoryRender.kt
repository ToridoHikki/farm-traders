package vn.minerva.travinh.func.medical.presentation.renderer

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout_cataloge_vholder.view.*
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.core.base.presentation.mvp.android.model.ViewRenderer
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.func.category.presentation.model.CategoryItemModel

class CategoryRender(mvpActivity: MvpActivity) : ViewRenderer<CategoryItemModel>(mvpActivity){
    override fun getLayoutId(): Int = R.layout.item_layout_cataloge_vholder

    override fun getModelClass(): Class<CategoryItemModel> = CategoryItemModel::class.java

    override fun bindView(model: CategoryItemModel, viewRoot: View) {
        Glide.with(context).load(model.photo).into(viewRoot.ivCategory)
        viewRoot.tvCategory.text = model.name

    }

}