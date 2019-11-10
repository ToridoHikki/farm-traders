package vn.silverbot99.farm_traders.func.empty;

import org.jetbrains.annotations.NotNull;

import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView;
import vn.silverbot99.core.base.presentation.mvp.android.MvpFragment;
/*
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView;
import vn.minerva.core.base.presentation.mvp.android.MvpFragment;*/

public class EmptyFragment extends MvpFragment {

    @NotNull
    @Override
    public AndroidMvpView createAndroidMvpView() {
        return new EmptyView(getMvpActivity(), new EmptyView.ViewCreator(getMvpActivity(), null));
    }
}
