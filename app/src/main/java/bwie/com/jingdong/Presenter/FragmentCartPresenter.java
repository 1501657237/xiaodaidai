package bwie.com.jingdong.Presenter;

import bwie.com.jingdong.Model.CartModel;
import bwie.com.jingdong.Model.bean.CartBean;
import bwie.com.jingdong.Presenter.i_presenter.CartPresenterInter;
import bwie.com.jingdong.View.Iview.FragmentCartInter;
import bwie.com.jingdong.View.fragment.FragmentShoppingCart;

/**
 * Created by LENOVO on 2018/3/17.
 */

public class FragmentCartPresenter implements CartPresenterInter {
    private  FragmentCartInter fragmentCartInter;
    private CartModel cartModel;

    public FragmentCartPresenter(FragmentCartInter fragmentCartInter) {
        this.fragmentCartInter = fragmentCartInter;

        cartModel = new CartModel(this);
    }
    public void getCartData(String selectCart, String uid) {

        cartModel.getCartData(selectCart,uid);

    }
    @Override
    public void getCartDataNull() {
        fragmentCartInter.getCartDataNull();
    }

    @Override
    public void getCartDataSuccess(CartBean cartBean) {
        fragmentCartInter.getCartDataSuccess(cartBean);
    }
}
