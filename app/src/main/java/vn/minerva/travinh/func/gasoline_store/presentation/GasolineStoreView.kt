package vn.minerva.travinh.func.gasoline_store.presentation

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.github.vivchar.rendererrecyclerviewadapter.LoadMoreViewRenderer
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.mvpactivity.showErrorAlert
import kotlinex.number.getValueOrDefaultIsZero
import kotlinx.android.synthetic.main.layout_gasoline_store_list.view.*
import kotlinx.android.synthetic.main.layout_medical_list.view.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import vn.minerva.core.app.view.SimpleDividerItemDecoration
import vn.minerva.core.app.view.loading.Loadinger
import vn.minerva.core.base.domain.listener.OnActionNotify
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.core.base.presentation.mvp.android.list.GridRenderConfigFactory
import vn.minerva.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp
import vn.minerva.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import vn.minerva.travinh.R
import vn.minerva.travinh.app.presentation.navigater.AndroidScreenNavigator
import vn.minerva.travinh.func.gasoline_store.presentation.model.GasolineViewModel
import vn.minerva.travinh.func.gasoline_store.presentation.renderer.GasolineStoreRenderer
import vn.minerva.travinh.func.medical.presentation.model.MedicalViewModel
import java.util.*


class GasolineStoreView(mvpActivity: MvpActivity, viewCreator: GasolineStoreView.ViewCreator) :
    AndroidMvpView(mvpActivity,viewCreator),
    GasolineStoreContract.View {
    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        LayoutViewCreator(R.layout.layout_gasoline_store_list, context, viewGroup)


    //private val resourceProvider = MedicalResourceProvider()
    private val mPresenter = GasolineStorePresenter(AndroidScreenNavigator(mvpActivity))
    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private var listViewMvp: ListViewMvp? = null
    private val resourceProvider = GasolineStoreResourceProvider()
    private var listData: MutableList<ViewModel> = mutableListOf()
    private var tempListData: MutableList<ViewModel> = mutableListOf()
    private var totalGasStore: Int = 0
    private var page: Int = 1

    private val mOnClickMessageItem: OnItemRvClickedListener<ViewModel> = object : OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            runWithCheckMultiTouch("ListMenuView_click_item", object : OnActionNotify {
                override fun onActionNotify() {
                    if (dataItem is GasolineViewModel) {
                        Log.d("âsasa","onItemClicked ${dataItem.storeLap} ${dataItem.storeLon}")
                        mPresenter.gotoGasStationDetail(id = dataItem.storeId)
                    }
                }
            })
        }
    }
    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        reloadData()
    }

    private fun reloadData() {
        totalGasStore = 0
        mPresenter.getGasStationList(
            page = page)
        view.vGasRefresh.isRefreshing = false
    }
    private var loadMoreEvent = object : OnActionNotify{
        override fun onActionNotify() {
            runWithCheckMultiTouch("onLoadMoreData",object:OnActionNotify {
                override fun onActionNotify() {
                    if (listData.size < totalGasStore) {
                        listViewMvp?.showLoadMore()
                        mPresenter.getGasStationList(page = page)
                    }
                }

            })
        }
    }
    private fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (listViewMvp?.items?.size == 2) {
                    return 2
                }
                return 1
            }
        }//
    }
    private val renderInput = GridRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = GridRenderConfigFactory.Orientation.VERTICAL,
        loadMoreConfig = ListViewMvp.LoadMoreConfig(loadMoreEvent, LoadMoreViewRenderer(R.layout.item_layout_load_more)),
        decoration = SimpleDividerItemDecoration(mvpActivity, R.drawable.line_divider_grey_c5c5c7),
        spanCount = 1,
        spanSizeLookup = getSpanSizeLookup()
    )
    private val renderConfig = GridRenderConfigFactory(renderInput).create()

    override fun initCreateView() {
        val toolbar = view.toolbar
        mvpActivity.setSupportActionBar(toolbar)
        mvpActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mvpActivity.supportActionBar?.setDisplayShowHomeEnabled(true)
        mvpActivity.supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbar.setNavigationOnClickListener {mvpActivity.onBackPressed()}
        view.searchView.setOnSearchClickListener { view.tvTitleScreen.visibility = View.GONE }
        view.tvTitleScreen.text = resourceProvider.getTitleGasStore()

        view.vGasRefresh.setOnRefreshListener(onRefreshListener)
        view.vGasRefresh.isRefreshing = false

        initSearchView()
        listViewMvp = ListViewMvp(mvpActivity, view.rvGasolineStore, renderConfig)
        listViewMvp?.createView()
        listViewMvp?.addViewRenderer(GasolineStoreRenderer(mvpActivity))
        listViewMvp?.setOnItemRvClickedListener(mOnClickMessageItem)
    }



    private fun fitlerText(text: String) {
        var charText:CharSequence = text.toLowerCase(Locale.getDefault())
        listData.clear()
        if (charText.length == 0) {
            listData.addAll(tempListData)
        } else {
            for (wp in tempListData) {
                if(wp is GasolineViewModel){
                    if (wp.storeName.toLowerCase(Locale.getDefault()).contains(charText)) {
                        listData.add(wp)
                    }
                }
            }
        }
        listViewMvp?.setItems(listData)
        listViewMvp?.notifyDataChanged()

    }

    override fun initData() {
        super.initData()
        totalGasStore = 0
        page = 1
        mPresenter.getGasStationList(
            page = page
        )
    }
    private fun initSearchView() {
        view.searchView.visibility = View.VISIBLE
        view.searchView.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String): Boolean {
                runWithCheckMultiTouch("ListMenuView_click_item", object : OnActionNotify{
                    override fun onActionNotify() {
                        fitlerText(p0)
                    }

                })
                return false
            }

            override fun onQueryTextChange(p0: String): Boolean {
                runWithCheckMultiTouch("ListMenuView_click_item", object : OnActionNotify{
                    override fun onActionNotify() {
                        fitlerText(p0)
                    }

                })
                return false
            }

        });
        view.searchView.setOnCloseListener(object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                view.searchView.visibility = View.GONE
                view.tvTitleScreen.visibility = View.VISIBLE
                return true
            }

        })

    }


/*    override fun loadData() {
        mPresenter.getGasStationList(
            page = -1
        )
    }*/

    override fun showGasolineStoreDetail(data: List<ViewModel>,totalGasStore: Int) {
        if (page == 1) {
            view.tvTotalGasStore.text = "${totalGasStore} ${resourceProvider.getTitleGasStore()}"
            this.totalGasStore = totalGasStore
            listData.clear()
        }
        if (data.isNotEmpty()) {
            page++
            listData.addAll(data)
            tempListData.addAll(listData)
        }
        listViewMvp?.setItems(listData)
        listViewMvp?.notifyDataChanged()
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

}
