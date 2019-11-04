package vn.silverbot99.farm_traders.func.main.presentation

import android.content.Context
import android.support.v4.view.GravityCompat
import android.view.ViewGroup
import kotlinex.context.showAlert
import kotlinex.mvpactivity.showErrorAlert
import kotlinx.android.synthetic.main.layout_app_bar_menu_main.view.*
import kotlinx.android.synthetic.main.layout_main.view.*
import kotlinx.android.synthetic.main.layout_nav_header_main.view.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import vn.silverbot99.core.app.util.image.GlideImageHelper
import vn.silverbot99.core.app.view.loading.Loadinger
import vn.silverbot99.core.base.domain.listener.OnActionData
import vn.silverbot99.core.base.domain.listener.OnActionNotify
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.app.config.ConfigUtil
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator
import vn.silverbot99.farm_traders.func.empty.EmptyView
import vn.silverbot99.farm_traders.func.main.data.ActionMenu
import vn.silverbot99.farm_traders.func.main.domain.MainConfigUseCase
import vn.silverbot99.farm_traders.func.main.presentation.menu.ListMenuView
import vn.silverbot99.farm_traders.func.main.presentation.model.MenuViewModel

abstract class MainView(mvpActivity: MvpActivity, viewCreator: ViewCreator) : AndroidMvpView(mvpActivity, viewCreator), MainContract.MainView {

    protected val androidScreenNavigator = AndroidScreenNavigator(mvpActivity)

    protected val mainPresenter = MainPresenter(androidScreenNavigator)
    private var listMenuView: ListMenuView? = null
    private var emptyView: EmptyView? = null

    protected val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    protected var actionMenuCurrent = ActionMenu.ACTION_DEFAULT
    private val resourceProvider = MainResourceProvider()

    override fun initCreateView() {
        initMenuView()
        initHeaderView()
        initLayoutView()
        initEmptyView()
        //initMedicalView()
    }

    private fun initHeaderView() {
        val passport = ConfigUtil.passport
        passport?.let {
            view.text_view_nav_header_full_name.text = it.ruleName
            view.text_view_nav_header_email.text = it.fullName
            val imageHelper = GlideImageHelper(mvpActivity)
            imageHelper.loadAvatar(view.image_view_nav_header_avatar, it.avatarUrl)
        }
        view.navigation_header_container.setOnClickListener {
            runWithCheckMultiTouch("onActionOpenProfile", object : OnActionNotify {
                override fun onActionNotify() {
                    handleOnClickItemMenu(MenuViewModel("", 0, false, false, ActionMenu.ACTION_MENU_HEADER_PROFILE))
                }
            })
        }
    }

    abstract fun initLayoutView()
    abstract fun showHomeView()
    abstract fun handlerClickMenu(action: ActionMenu): Boolean

    override fun showError(msgError: String) {
        mvpActivity.showErrorAlert(msgError)
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    private fun initEmptyView() {
        emptyView = EmptyView(mvpActivity, EmptyView.ViewCreator(mvpActivity, null))
        emptyView?.let {
            addLifeCycle(it)
        }
    }

    override fun initData() {
        super.initData()
        mainPresenter.loadConfigData()
    }


    override fun handleAfterLoadingConfig(output: MainConfigUseCase.Output) {
        showHomeView()
    }

    override fun startMvpView() {
        mainPresenter.attachView(this)
        super.startMvpView()
        listMenuView?.startMvpView()//%%%
        //lát thêm MedicalVIew vào giống vậy
    }

    private fun initMenuView() {
        listMenuView = ListMenuView(mvpActivity, view.rvMenuItem, view.drawerLayout, view.toolbar, object :
            OnActionData<MenuViewModel> {
            override fun onAction(data: MenuViewModel) {
                closeMenu()
                handleOnClickItemMenu(data)
            }
        })
        listMenuView?.let { listMenuView ->
            listMenuView.createView()
            addLifeCycle(listMenuView)
        }
    }


    protected fun handleOnClickItemMenu(data: MenuViewModel) {
        if (actionMenuCurrent != data.action) {
            if (data.action != ActionMenu.ACTION_MENU_LOGOUT) {
                actionMenuCurrent = data.action
            }
            if (!handlerClickMenu(data.action)) {
                when (data.action) {
                    ActionMenu.ACTION_MENU_LOGOUT -> {
                        logout()
                    }
                    ActionMenu.ACTION_MENU_HEADER_PROFILE -> {
                        showProfileView()
                    }

                    else -> showEmptyView(data)
                }

            }
        }
    }



    private fun closeMenu() {
        if (view.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            view.drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    override fun stopMvpView() {
        super.stopMvpView()
        mainPresenter.detachView()
    }

    private fun showEmptyView(model: MenuViewModel) {
        addMainContentWithView(emptyView)
    }

    private fun showProfileView() {
        mainPresenter.showProfileView()
    }

    fun addMainContentWithView(mvpView: AndroidMvpView?) {
        mvpView?.let {
            view.vgContent.removeAllViews()
            view.vgContent.addView(it.createView())
        }
    }


    private fun logout() {
        mvpActivity.showAlert(resourceProvider.getLogoutMessageDialog(), "", object : OnActionNotify {
            override fun onActionNotify() {
                ConfigUtil.savePassport(null)
                mainPresenter.gotoLoginActivity()
            }
        }, object : OnActionNotify {
            override fun onActionNotify() {

            }
        })
    }

    override fun resetActionDefault() {
        actionMenuCurrent = ActionMenu.ACTION_DEFAULT
    }

    class ViewCreator(context: Context, viewGroup: ViewGroup?) : LayoutViewCreator(R.layout.layout_main, context, viewGroup)
}