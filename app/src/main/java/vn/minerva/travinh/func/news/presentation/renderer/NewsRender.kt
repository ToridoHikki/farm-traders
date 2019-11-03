package vn.minerva.travinh.func.news.presentation.renderer

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout_gasoline_store_detail_vholder.view.*
import kotlinx.android.synthetic.main.item_layout_news_list_vhoder.view.*
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.core.base.presentation.mvp.android.model.ViewRenderer
import vn.minerva.travinh.R
import vn.minerva.travinh.func.news.presentation.model.NewsViewModel

class NewsRender(mvpActivity: MvpActivity) : ViewRenderer<NewsViewModel>(mvpActivity){
    override fun getLayoutId(): Int = R.layout.item_layout_news_list_vhoder

    override fun getModelClass(): Class<NewsViewModel> = NewsViewModel::class.java

    override fun bindView(model: NewsViewModel, viewRoot: View) {
        Glide.with(context).load(model.thumbnail).into(viewRoot.ivNews)
        viewRoot.tvNewsTitle.text = model.name
        viewRoot.tvNewsContent.text = model.desc

    }

}