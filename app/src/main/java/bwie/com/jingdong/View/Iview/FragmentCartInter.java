package bwie.com.jingdong.View.Iview;

import bwie.com.jingdong.Model.bean.CartBean;

/**
 * Created by LENOVO on 2018/3/17.
 */

public interface FragmentCartInter {
    void getCartDataNull();

    void getCartDataSuccess(CartBean cartBean);
}
