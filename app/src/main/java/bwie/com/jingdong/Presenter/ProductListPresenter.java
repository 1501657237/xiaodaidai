package bwie.com.jingdong.Presenter;

import bwie.com.jingdong.Model.ProductListModel;
import bwie.com.jingdong.Model.bean.ProductListBean;
import bwie.com.jingdong.Presenter.i_presenter.ProductListPresenterInter;
import bwie.com.jingdong.View.Iview.ProductListActivityInter;
import bwie.com.jingdong.View.activity.MainActivity_02;

/**
 * Created by LENOVO on 2018/3/30.
 */

public class ProductListPresenter  implements ProductListPresenterInter {
    private ProductListModel productListModel;
    private ProductListActivityInter productListActivityInter;

    public ProductListPresenter(ProductListActivityInter productListActivityInter) {
        this.productListActivityInter = productListActivityInter;

        productListModel = new ProductListModel(this);
    }

    public void getProductData(String seartchUrl, String keywords, int page) {

        productListModel.getProductData(seartchUrl,keywords,page);
    }

    @Override
    public void onSuccess(ProductListBean productListBean) {
        productListActivityInter.getProductDataSuccess(productListBean);
    }
}
