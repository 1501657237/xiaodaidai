package bwie.com.jingdong.Presenter.i_presenter;

import bwie.com.jingdong.Model.bean.CartBean;

/**
 * Created by LENOVO on 2018/3/21.
 */

public interface CartPresenterInter {
    void getCartDataNull();

    void getCartDataSuccess(CartBean cartBean);

}
