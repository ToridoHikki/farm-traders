package vn.minerva.core.base.utils

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.net.ConnectivityManager
import android.support.annotation.*
import android.support.v4.content.ContextCompat
import vn.minerva.core.app.common.AppConfigs

class Util {
    class Resource {
        companion object {
            @JvmStatic
            fun getString(@StringRes idStr: Int): String =
                    AppConfigs.instance.getBaseApplication().getString(idStr)

            @JvmStatic
            fun getIntResource(@IntegerRes intRes: Int): Int {
                return AppConfigs.instance.getBaseApplication().resources.getInteger(intRes)
            }

            @JvmStatic
            fun getColor(colorId: Int): Int =
                    ContextCompat.getColor(AppConfigs.instance.getBaseApplication(), colorId)

            @JvmStatic
            fun getDrawable(@DrawableRes drawableId: Int): Drawable {
                return ContextCompat.getDrawable(AppConfigs.instance.getBaseApplication(), drawableId)!!
            }

            @JvmStatic
            fun changeDrawableColor(background: Drawable, @ColorInt colorToSet: Int) {
                when (background) {
                    is ShapeDrawable -> {
                        // cast to 'ShapeDrawable'
                        val shapeDrawable = background.mutate() as ShapeDrawable
                        shapeDrawable.paint.color = colorToSet
                    }
                    is GradientDrawable -> {
                        // cast to 'GradientDrawable'
                        val gradientDrawable = background.mutate() as GradientDrawable
                        gradientDrawable.setColor(colorToSet)
                    }
                    is ColorDrawable -> {
                        // alpha value may need to be set again after this call
                        val colorDrawable = background.mutate() as ColorDrawable
                        colorDrawable.color = colorToSet
                    }
                }
            }

            @JvmStatic
            fun getIntDimen(@DimenRes id: Int): Int {
                return AppConfigs.instance.getBaseApplication().resources.getDimension(id).toInt()
            }
        }
    }

    class Func {
        companion object {
            @JvmStatic
            fun isNetworkConnected(): Boolean {
                val cm =AppConfigs.instance.getBaseApplication().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork = cm.activeNetworkInfo
                return activeNetwork != null && activeNetwork.isConnectedOrConnecting
            }
        }
    }

}