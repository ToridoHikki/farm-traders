package vn.silverbot99.core.app.view

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class CustomViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {
    var enabledTouch = true
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if (this.enabledTouch) {
            return super.onTouchEvent(ev)
        }
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (this.enabledTouch) {
            return super.onInterceptTouchEvent(ev)
        }
        return false
    }
}