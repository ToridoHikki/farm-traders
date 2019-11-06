package vn.silverbot99.farm_traders.func.splash.presentation

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.ViewGroup
import kotlinex.mvpactivity.showErrorAlert
import kotlinx.android.synthetic.main.layout_splash.view.*
import kotlinx.android.synthetic.main.layout_view_logo.view.*
import vn.silverbot99.core.app.common.AppConfigs
import vn.silverbot99.core.app.util.Utils
import vn.silverbot99.core.base.domain.listener.OnActionNotify
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.core.base.presentation.mvp.android.lifecycle.PermissionsResult
import vn.silverbot99.core.base.presentation.mvp.android.lifecycle.ViewResult
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.app.config.ConfigUtil
import vn.silverbot99.farm_traders.app.data.network.response.AppVersionResponse
import vn.silverbot99.farm_traders.app.data.network.response.PassportResponse
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator
import java.util.*
import android.support.v4.os.HandlerCompat.postDelayed



class SplashView(mvpActivity: MvpActivity, viewCreator: ViewCreator) : AndroidMvpView(mvpActivity, viewCreator),
        SplashContract.View {

    private val splashPresenter: SplashPresenter = SplashPresenter(
            screenNavigator = AndroidScreenNavigator(mvpActivity)
    )
    private val resourceProvider = SplashResourceProvider(mvpActivity)
    //Attach view
    override fun startMvpView() {
        splashPresenter.attachView(this)
        super.startMvpView()
    }

    override fun initCreateView() {
        statusCheck()
    }

    //Start register permission
    override fun initData() {
        super.initData()
        statusCheck()
    }

    private fun statusCheck() {
        val manager = mvpActivity.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            showAlertMessageNoGps()
        } else {
            splashPresenter.registerAppPermission()
        }
    }

    override fun handleAfterRequestPermission() {
/*        view.image_view_logo.animate()
                .alpha(1.0f)
                .setDuration(5000)
                .withEndAction {
                    val manager = mvpActivity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                    splashPresenter.checkLocation(manager)
                }
                .start()*/
        val handler = Handler()
        handler.postDelayed(Runnable {
            val manager = mvpActivity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            splashPresenter.checkLocation(manager)
        }, 2000)
    }
    //End register permission

    //Start ic_overview_check version
//    private fun showDownLoadNewVersion(isForce: Boolean) {
//        val builder = AlertDialog.Builder(mvpActivity)
//                .setCancelable(false)
//                .setTitle(R.string.title_update_app)
//                .setMessage(R.string.msg_update_app)
//                .setPositiveButton(R.string.ACTION_UPDATE) { dialog, _ ->
//                    val appPackageName = AppConfigs.instance.getBaseApplication()
//                            .packageName // getPackageName() from Context or Activity object
//                    try {
//                        mvpActivity.startActivity(
//                                Intent(
//                                        Intent.ACTION_VIEW,
//                                        Uri.parse("market://details?id=$appPackageName")
//                                )
//                        )
//                    } catch (anfe: android.content.ActivityNotFoundException) {
//                        mvpActivity.startActivity(
//                                Intent(
//                                        Intent.ACTION_VIEW,
//                                        Uri.parse("https://play.google.com/store_red/apps/details?id=$appPackageName")
//                                )
//                        )
//                    }
//
//                    dialog.dismiss()
//                    mvpActivity.finish()
//                }
//                .setNegativeButton(R.string.ACTION_CLOSE) { dialog, _ ->
//                    dialog.dismiss()
//                    mvpActivity.finish()
//                }
//
//        if (!isForce) {
//            builder.setNegativeButton(R.string.ACTION_CLOSE) { dialog, _ ->
//                dialog.dismiss()
//            }
//            nextActivity()
//        }
//        builder.show()
//    }
//
//    override fun handleAfterLoadAppVersion(data: AppVersionResponse) {
//        val versionCurrent = Utils.getVersionCode(mvpActivity)
//        val newVersion = data.version.toInt()
//        if (versionCurrent < newVersion) {
//            showDownLoadNewVersion(data.forceFlag)
//        } else {
//            nextActivity()
//        }
//    }

    override fun nextActivity() {
        val passportUid: String? = ConfigUtil.passportUid

        if (passportUid == null) {
            //splashPresenter.gotoLoginActivity()
            splashPresenter.gotoLoginActivity()
        } else {
            //splashPresenter.reLogin()
            splashPresenter.gotoMainActivity()

        }
    }

//    override fun handleAfterReLogin(data: PassportResponse) {
//        ConfigUtil.saveDateSelected(Calendar.getInstance())
//        ConfigUtil.savePassport(data)
//        ConfigUtil.saveIsFirstLoginApp(true)
//        //splashPresenter.gotoMainActivity()
//        splashPresenter.gotoSignUpActivity()
//    }

    //End ic_overview_check version
    //Start show error
//    override fun showErrorDialog(msg: String) {
//        mvpActivity.showErrorAlert(msg, onActionNotify = object : OnActionNotify {
//            override fun onActionNotify() {
//                runWithCheckMultiTouch("onActionNotify", object : OnActionNotify {
//                    override fun onActionNotify() {
//                        splashPresenter.loadAppVersion()
//                    }
//                })
//            }
//        })
//    }
    //End show error

    //Start Request permission
    override fun onRequestPermissionsResult(permissionsResult: PermissionsResult) {
        super.onRequestPermissionsResult(permissionsResult)
        if (permissionsResult.requestCode == REQUEST_ALL_PERMISSION) {
            if (permissionsResult.grantResults.size == 1 && permissionsResult.grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                splashPresenter.registerAppPermission()
            }
        }
    }

    override fun showRequestPermission(permission: String): Boolean {
        if (ContextCompat.checkSelfPermission(mvpActivity, permission) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(mvpActivity, permission)) {
            Snackbar.make(
                    view, R.string.msg_need_permission,
                    Snackbar.LENGTH_INDEFINITE
            ).setAction(
                    android.R.string.ok
            ) { _ ->
                ActivityCompat.requestPermissions(mvpActivity, arrayOf(permission), REQUEST_ALL_PERMISSION)
            }
        } else {
            ActivityCompat.requestPermissions(mvpActivity, arrayOf(permission), REQUEST_ALL_PERMISSION)
        }
        return false
    }
    //End Request permission

    //Start ic_overview_check location
    override fun onViewResult(viewResult: ViewResult) {
        super.onViewResult(viewResult)
        val code = viewResult.requestCode
        if (REQUEST_GPS_MANAGER == code || LOCATION_SETTINGS_REQUEST == code) {
            statusCheck()
        }
    }

    override fun showAlertMessageNoGps() {
        val builder = AlertDialog.Builder(mvpActivity)
        builder.setMessage(R.string.mgs_open_location)
                .setCancelable(false)
                .setPositiveButton(R.string.ACTION_WANT) { _, _ ->
                    mvpActivity.startActivityForResult(
                            Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS),
                            REQUEST_GPS_MANAGER
                    )
                }
                .setNegativeButton(R.string.ACTION_NO) { dialog, _ ->
                    dialog.cancel()
                    mvpActivity.finish()
                }
        val alert = builder.create()
        alert.show()
    }

    //End ic_overview_check location
    //Stop view
    override fun stopMvpView() {
        splashPresenter.detachView()
        super.stopMvpView()
    }

    //Create view layout
    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
            AndroidMvpView.LayoutViewCreator(R.layout.layout_splash, context, viewGroup)

    companion object {
        const val REQUEST_ALL_PERMISSION = 0
        const val REQUEST_GPS_MANAGER = 1
        const val LOCATION_SETTINGS_REQUEST = 2
    }
}

