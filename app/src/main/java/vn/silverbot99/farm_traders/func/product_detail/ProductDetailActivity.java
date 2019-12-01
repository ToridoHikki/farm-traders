package vn.silverbot99.farm_traders.func.product_detail;

import org.jetbrains.annotations.NotNull;

import be.trikke.intentbuilder.BuildIntent;
import be.trikke.intentbuilder.Extra;
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView;
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity;
import vn.silverbot99.farm_traders.func.farm_detail.presentation.FarmDetailView;
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel;
import vn.silverbot99.farm_traders.func.product_detail.presentation.ProductDetailView;
import vn.silverbot99.farm_traders.func.product_list.presentation.model.ProductListItemModel;

@BuildIntent
public class ProductDetailActivity extends MvpActivity {
    @Extra
    ProductListItemModel product;

    @NotNull
    @Override
    public AndroidMvpView createAndroidMvpView() {
        return new ProductDetailView(this,new ProductDetailView.ViewCreator(this,null),product);
    }
}
