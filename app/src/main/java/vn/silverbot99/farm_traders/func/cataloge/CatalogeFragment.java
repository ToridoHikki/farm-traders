package vn.silverbot99.farm_traders.func.cataloge;

import org.jetbrains.annotations.NotNull;

import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView;
import vn.silverbot99.core.base.presentation.mvp.android.MvpFragment;
import vn.silverbot99.farm_traders.func.cataloge.presentation.CatalogeView;

public class CatalogeFragment extends MvpFragment {
    @NotNull
    @Override
    public AndroidMvpView createAndroidMvpView() {
        return new CatalogeView(getMvpActivity(), new CatalogeView.ViewCreator(getMvpActivity(), null));
    }
}
