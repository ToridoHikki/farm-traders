package vn.silverbot99.farm_traders.func.product_list;

import org.jetbrains.annotations.NotNull;

import be.trikke.intentbuilder.BuildIntent;
import be.trikke.intentbuilder.Extra;
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView;
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity;
import vn.silverbot99.farm_traders.func.product_list.presentation.ProductListView;

@BuildIntent
public class ProductListActivity extends MvpActivity {
    @Extra
    String categoryID = "";
    @NotNull
    @Override
    public AndroidMvpView createAndroidMvpView() {
        return new ProductListView(this,new ProductListView.ViewCreator(this,null),categoryID);
    }
}
