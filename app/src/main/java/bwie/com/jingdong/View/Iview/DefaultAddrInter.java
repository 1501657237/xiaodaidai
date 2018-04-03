package bwie.com.jingdong.View.Iview;

import bwie.com.jingdong.Model.bean.DefaultAddrBean;

/**
 * Created by LENOVO on 2018/3/25.
 */

public interface DefaultAddrInter {
    void onGetDefaultAddrSuccess(DefaultAddrBean defaultAddrBean);

    void onGetDefaultAddrEmpty();
}
