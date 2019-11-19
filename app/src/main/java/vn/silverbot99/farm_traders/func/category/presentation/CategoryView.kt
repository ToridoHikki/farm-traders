package vn.silverbot99.farm_traders.func.category.presentation

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.mvpactivity.showErrorAlert
import kotlinx.android.synthetic.main.layout_cataloge.view.*
import vn.minerva.travinh.func.medical.presentation.renderer.CategoryRender
import vn.silverbot99.core.app.view.loading.Loadinger
import vn.silverbot99.core.base.domain.listener.OnActionNotify
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.core.base.presentation.mvp.android.list.GridRenderConfigFactory
import vn.silverbot99.core.base.presentation.mvp.android.list.ListViewMvp
import vn.silverbot99.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator
import vn.silverbot99.farm_traders.func.category.presentation.model.CategoryItemModel

class CategoryView(mvpActivity: MvpActivity, viewCreator: ViewCreator) : AndroidMvpView(mvpActivity,viewCreator),
    CategoryContract.View {

    private var listViewMvp: ListViewMvp? = null
    private var listData: MutableList<ViewModel> = mutableListOf()
    private val mPresenter = CategoryPresenter(AndroidScreenNavigator(mvpActivity))
    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val renderInput = GridRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = GridRenderConfigFactory.Orientation.VERTICAL,
        spanCount = 2,
        spanSizeLookup = getSpanSizeLookup()
    )
    private val renderConfig = GridRenderConfigFactory(renderInput).create()
    private fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (listViewMvp?.items?.size == 2) {
                    return 2
                }
                return 1
            }
        }
    }

    override fun showDetailInfo(data: List<ViewModel>) {
        if(data.isNotEmpty()){
            listData.addAll(data)
        }
        listViewMvp?.setItems(this.listData)
        listViewMvp?.notifyDataChanged()
    }

    override fun initCreateView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvCatalogeProduct,renderConfig)
        listViewMvp?.createView()
        listViewMvp?.addViewRenderer(CategoryRender(mvpActivity))
        listViewMvp?.setOnItemRvClickedListener(mOnClickCategoryItem)

    }
/*    private val mOnClickMessageItem = (object : View.OnClickListener{
        override fun onClick(v: View?) {
            EmptyFragment()
        }
    })*/
    private val mOnClickCategoryItem: OnItemRvClickedListener<ViewModel> = object : OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            runWithCheckMultiTouch("ListMenuView_click_item", object : OnActionNotify {
                override fun onActionNotify() {
                    if (dataItem is CategoryItemModel) {
                        Log.d("item","onItemClicked ${dataItem.name}")
                        mPresenter.gotoProductList(dataItem.categoryId)
                    }
                }
            })
        }
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
        mPresenter.getCategoryList()
    }


    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_cataloge, context, viewGroup)
}