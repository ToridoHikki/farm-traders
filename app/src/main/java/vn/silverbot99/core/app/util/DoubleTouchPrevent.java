package vn.silverbot99.core.app.util;

import android.os.SystemClock;
import android.support.v4.util.ArrayMap;

public class DoubleTouchPrevent {
    private ArrayMap<String, Long> touchActionMap = new ArrayMap<>();
    private static final int TIME_PREVENT_DOUBLE_TOUCH = 300;
    private long minTime;

    DoubleTouchPrevent() {
        this(TIME_PREVENT_DOUBLE_TOUCH);
    }

    public DoubleTouchPrevent(long minTimeTouch) {
        minTime = minTimeTouch;
    }

    public boolean check(String actionKey) {
        boolean result = false;
        long realTime = SystemClock.elapsedRealtime();

        Long lastTimeOb = touchActionMap.get(actionKey);
        long lastTime = lastTimeOb != null ? lastTimeOb : 0;

        if (realTime - lastTime >= minTime) {
            lastTimeOb = realTime;
            touchActionMap.put(actionKey, lastTimeOb);
            result = true;
        }

        return result;
    }

    public void updateTimePreventDoubleTouch(int timePreventDoubleTouch) {
        minTime = timePreventDoubleTouch;
    }
}
