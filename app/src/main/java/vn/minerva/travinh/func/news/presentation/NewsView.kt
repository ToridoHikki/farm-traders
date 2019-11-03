package vn.minerva.travinh.func.news.presentation

import android.content.Context
import android.view.ViewGroup
import android.widget.Toast
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.mvpactivity.showErrorAlert
import kotlinx.android.synthetic.main.layout_medical_list.view.*
import kotlinx.android.synthetic.main.layout_news_list.view.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import vn.minerva.core.app.view.SimpleDividerItemDecoration
import vn.minerva.core.app.view.loading.Loadinger
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.core.base.presentation.mvp.android.list.GridRenderConfigFactory
import vn.minerva.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp
import vn.minerva.travinh.R
import vn.minerva.travinh.app.presentation.navigater.AndroidScreenNavigator
import vn.minerva.travinh.func.medical.presentation.MedicalContract
import vn.minerva.travinh.func.medical.presentation.MedicalPresenter
import vn.minerva.travinh.func.medical.presentation.MedicalView
import vn.minerva.travinh.func.medical.presentation.renderer.MedicalRender
import vn.minerva.travinh.func.news.presentation.renderer.NewsRender

class NewsView(mvpActivity: MvpActivity, viewCreator: NewsView.ViewCreator) :
    AndroidMvpView(mvpActivity,viewCreator),
    NewsContract.View {
    private val mPresenter = NewsPresenter(AndroidScreenNavigator(mvpActivity))
    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private var listData: MutableList<ViewModel> = mutableListOf()
    private var listViewMvp: ListViewMvp? = null
    private val resourceProvider = NewsResourceProvider()
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()

    override fun showNewsList(data: List<ViewModel>) {
        listData.clear()
        if (data.isNotEmpty()) {
            listData.addAll(data)
        }
        listViewMvp?.setItems(listData)
        listViewMvp?.notifyDataChanged()
    }

    override fun initCreateView() {
        val toolbar = view.toolbar
        mvpActivity.setSupportActionBar(toolbar)
        mvpActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mvpActivity.supportActionBar?.setDisplayShowHomeEnabled(true)
        mvpActivity.supportActionBar?.setDisplayShowTitleEnabled(false)
        view.tvTitleScreen.text = resourceProvider.getNewsTitile()
        toolbar.setNavigationOnClickListener { /*handleOnBackPress() */mvpActivity.onBackPressed()}


        listViewMvp = ListViewMvp(mvpActivity, view.rvNews,renderConfig)
        listViewMvp?.createView()
        listViewMvp?.addViewRenderer(NewsRender(mvpActivity))
//        listViewMvp?.setOnItemRvClickedListener(mOnClickMessageItem)
    }
    private fun handleOnBackPress() {
        mPresenter.gotoMainView()
    }

    override fun initData() {
        super.initData()
        mPresenter.getNewsList(page = 1)
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun showToast(message: String) {
        Toast.makeText(mvpActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun showError(message: String) {
        mvpActivity.showErrorAlert(message)
    }

    override fun startMvpView() {
        mPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }
    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_news_list, context, viewGroup)

}