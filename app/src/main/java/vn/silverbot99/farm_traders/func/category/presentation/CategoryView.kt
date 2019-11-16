package vn.silverbot99.farm_traders.func.category.presentation

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.view.ViewGroup
import android.widget.Toast
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.mvpactivity.showErrorAlert
import kotlinx.android.synthetic.main.layout_cataloge.view.*
import vn.minerva.travinh.func.medical.presentation.renderer.CategoryRender
import vn.silverbot99.core.app.view.SimpleDividerItemDecoration
import vn.silverbot99.core.app.view.loading.Loadinger
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.core.base.presentation.mvp.android.list.GridRenderConfigFactory
import vn.silverbot99.core.base.presentation.mvp.android.list.ListViewMvp
import vn.silverbot99.farm_traders.R
class CategoryView(mvpActivity: MvpActivity, viewCreator: ViewCreator) : AndroidMvpView(mvpActivity,viewCreator),
    CategoryContract.View {

    private var listViewMvp: ListViewMvp? = null
    private var listData: MutableList<ViewModel> = mutableListOf()
    private val mPresenter = CategoryPresenter()
    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val renderInput = GridRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = GridRenderConfigFactory.Orientation.VERTICAL,
        spanCount = 1,
        spanSizeLookup = getSpanSizeLookup()
    )
    private val renderConfig = GridRenderConfigFactory(renderInput).create()
    private fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (listViewMvp?.items?.size == 2) {
                    return 1
                }
                return 2
            }
        }
    }

    override fun showDetailInfo(data: List<ViewModel>) {
        if(data.isNotEmpty()){
            listData.addAll(data)
        }
        listViewMvp?.setItems(listData)
        listViewMvp?.notifyDataChanged()
    }

    override fun initCreateView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvCatalogeProduct,renderConfig)
        listViewMvp?.createView()
        listViewMvp?.addViewRenderer(CategoryRender(mvpActivity))
        //listViewMvp?.setOnItemRvClickedListener(mOnClickMessageItem)

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

    override fun initData() {
        super.initData()
        mPresenter.getCataloge()
    }

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_cataloge, context, viewGroup)
}