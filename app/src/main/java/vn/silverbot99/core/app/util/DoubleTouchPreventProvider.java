package vn.silverbot99.core.app.util;

import android.support.annotation.NonNull;

public class DoubleTouchPreventProvider {
    private static DoubleTouchPrevent doubleTouchPrevent = new DoubleTouchPrevent();

    @NonNull
    public static DoubleTouchPrevent createDoubleTouchPrevent() {
        return doubleTouchPrevent;
    }
}
