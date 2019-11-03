package vn.minerva.travinh.func.gasoline_store_detail;

import be.trikke.intentbuilder.BuildIntent;
import be.trikke.intentbuilder.Extra;
import org.jetbrains.annotations.NotNull;
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView;
import vn.minerva.core.base.presentation.mvp.android.MvpActivity;
import vn.minerva.travinh.func.gasoline_store_detail.presentation.GasolineStoreDetailView;

@BuildIntent
public class GasolineStoreDetailActivity extends MvpActivity {
    @Extra
    int id;

    @NotNull
    @Override
    public AndroidMvpView createAndroidMvpView() {
        return new GasolineStoreDetailView(this, new GasolineStoreDetailView.ViewCreator(this,null),id);
    }
}
