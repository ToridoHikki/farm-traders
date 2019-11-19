package vn.silverbot99.farm_traders.func.category;

import android.content.Context;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView;
import vn.silverbot99.core.base.presentation.mvp.android.MvpFragment;
import vn.silverbot99.farm_traders.func.category.presentation.CategoryView;

public class CategoryFragment extends MvpFragment {
    @NotNull
    @Override
    public AndroidMvpView createAndroidMvpView() {
        return new CategoryView(getMvpActivity(), new CategoryView.ViewCreator(getMvpActivity(), null));
    }

}
