package bwie.com.jingdong.View.Iview;

import bwie.com.jingdong.Model.bean.LoginBean;

/**
 * Created by LENOVO on 2018/3/21.
 */

public interface LoginActivityInter {
    void getLoginSuccess(LoginBean loginBean);


    void getLoginSuccessByQQ(LoginBean loginBean, String ni_cheng, String iconurl);

}
