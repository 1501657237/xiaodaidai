package bwie.com.jingdong.Presenter.i_presenter;

import bwie.com.jingdong.Model.CreateOrderBean;
import bwie.com.jingdong.Model.bean.DefaultAddrBean;

/**
 * Created by LENOVO on 2018/3/25.
 */

public interface GetDefaultAddrPresenterInter {
    void onGetDefaultAddrSuccess(DefaultAddrBean defaultAddrBean);

    void onGetDefaultAddrEmpty();
}
