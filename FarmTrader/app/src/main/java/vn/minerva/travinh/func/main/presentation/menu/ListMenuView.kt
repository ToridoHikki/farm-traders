package vn.minerva.travinh.func.main.presentation.menu

import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.synthetic.main.layout_main.view.*
import org.jetbrains.anko.collections.forEachWithIndex
import vn.minerva.core.app.domain.excecutor.EventFireUtil
import vn.minerva.core.base.domain.listener.OnActionData
import vn.minerva.core.base.domain.listener.OnActionNotify
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp
import vn.minerva.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import vn.minerva.travinh.R
import vn.minerva.travinh.func.main.data.ActionMenu
import vn.minerva.travinh.func.main.presentation.MainContract
import vn.minerva.travinh.func.main.presentation.model.MenuViewModel
import vn.minerva.travinh.func.main.presentation.renderer.MenuGroupViewRenderer
import vn.minerva.travinh.func.main.presentation.renderer.MenuViewRenderer


class ListMenuView(mvpActivity: MvpActivity, recyclerView: RecyclerView, private var drawerLayout: DrawerLayout, private var toolbar: Toolbar,
                   var onActionOnClickMenu: OnActionData<MenuViewModel>) : AndroidMvpView(mvpActivity, ViewRootViewCreator(view = recyclerView)),  MainContract.MenuView {
    private var lstMenuView: ListViewMvp? = null
    private val renderInputMenu = LinearRenderConfigFactory.Input(
            context = mvpActivity,
            orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )

    private val menuResourceProvider = ListMenuResourceProvider()
    private val listMenuPresenter = ListMenuPresenter(menuResourceProvider)
    private val renderConfigMenu = LinearRenderConfigFactory(renderInputMenu).create() //cái này khởi tạo cái line để ngăn cách thôi
    private var menuViewModels: MutableList<ViewModel> = mutableListOf()

    //
    private val mOnClickMenuItem: OnItemRvClickedListener<ViewModel> = object : OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            runWithCheckMultiTouch("ListMenuView_click_item", object : OnActionNotify {
                override fun onActionNotify() {
                    //tương tự bên này cũng thế. cái view này có 2 loại là MenuViewModel và MenuViewGroupModel
                    if (dataItem is MenuViewModel) {
                        if (dataItem.action != ActionMenu.ACTION_MENU_LOGOUT) {
                            resetSelected(position)
                        }
                        if (dataItem.isGroup) {

                        } else {
                            //Cái này là cái action để call lại cho thằng main biết mà chuyển layout
                            EventFireUtil.fireEvent(onActionOnClickMenu, dataItem)
                        }
                    }
                }
            })
        }
    }


    override fun initCreateView() {
        initMenuView()
        lstMenuView = ListViewMvp(mvpActivity, view.rvMenuItem, renderConfigMenu) // khởi tạo list
        lstMenuView?.createView()
        lstMenuView?.addViewRenderer(MenuViewRenderer(mvpActivity)) //add mấy cái Renderer (cái này là view holder)
        lstMenuView?.addViewRenderer(MenuGroupViewRenderer(mvpActivity)) //add mấy cái Renderer (cái này là view holder)
        lstMenuView?.setOnItemRvClickedListener(mOnClickMenuItem)
        //Anh có nhiều kiểu thì add vô hết
    }

    private fun initMenuView() {
        mvpActivity.setSupportActionBar(toolbar)
        mvpActivity.supportActionBar?.setDisplayShowTitleEnabled(false)
        val toggle = ActionBarDrawerToggle(
                mvpActivity, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun resetSelected(position: Int) {
        menuViewModels.forEachWithIndex { index, itemModel ->
            if (itemModel is MenuViewModel)
                itemModel.isSelected = index == position
        }
        lstMenuView?.notifyDataChanged()
    }

    override fun initData() {
        listMenuPresenter.loadListMenu()
    }
    override fun startMvpView() {
        listMenuPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        listMenuPresenter.detachView()
        super.stopMvpView()
    }

    override fun renderListMenu(menuViewModels: MutableList<ViewModel>) {
        if (menuViewModels.isNotEmpty()) {
            this.menuViewModels.clear()
            this.menuViewModels.addAll(menuViewModels)
            lstMenuView?.setItems(this.menuViewModels)
            lstMenuView?.notifyDataChanged()
        }
    }
}