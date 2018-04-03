package bwie.com.jingdong.Presenter.i_presenter;

import bwie.com.jingdong.Model.bean.LoginBean;

/**
 * Created by LENOVO on 2018/3/21.
 */

public interface LoginPresenterInter {
    void onSuccess(LoginBean loginBean);


    void onSuccessByQQ(LoginBean loginBean, String ni_cheng, String iconurl);

}
