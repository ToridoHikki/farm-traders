package vn.silverbot99.core.app.view.loading;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import vn.silverbot99.core.app.view.ContentLoadingProgressBar;
import vn.silverbot99.farm_traders.R;

public class Loading extends FrameLayout {
    //UI
    private ContentLoadingProgressBar pbLoading;

    /**
     * This is the default view constructor. It requires a Context, and holds a reference to it.
     * If not cleaned up properly, memory will leak.
     *
     * @param context The Activity Context
     */
    public Loading(@NonNull final Context context) {
        super(context, null, R.attr.alertStyle);
        initView();
    }

    /**
     * This is the default view constructor. It requires a Context, and holds a reference to it.
     * If not cleaned up properly, memory will leak.
     *
     * @param context The Activity Context
     * @param attrs   View Attributes
     */
    public Loading(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs, R.attr.alertStyle);
        initView();
    }

    /**
     * This is the default view constructor. It requires a Context, and holds a reference to it.
     * If not cleaned up properly, memory will leak.
     *
     * @param context      The Activity Context
     * @param attrs        View Attributes
     * @param defStyleAttr Styles
     */
    public Loading(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.loadinger_loading_view, this);
        setHapticFeedbackEnabled(true);

        pbLoading = findViewById(R.id.pbLoading);
        pbLoading.setOnViewDisplayEvent(() -> Loading.this.setVisibility(VISIBLE));
        pbLoading.setOnViewHideEvent(() -> Loading.this.setVisibility(GONE));
    }

    /* Clean Up Methods */

    /**
     * Cleans up the currently showing alert view.
     */
    public void hide() {
        pbLoading.hide();
    }

    public void show() {
        pbLoading.show();
    }
}