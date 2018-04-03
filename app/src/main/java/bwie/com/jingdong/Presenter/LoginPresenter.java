package bwie.com.jingdong.Presenter;

import bwie.com.jingdong.Model.LoginModel;
import bwie.com.jingdong.Model.bean.LoginBean;
import bwie.com.jingdong.Presenter.i_presenter.LoginPresenterInter;
import bwie.com.jingdong.View.Iview.LoginActivityInter;
import bwie.com.jingdong.View.activity.LoginActivity;

/**
 * Created by LENOVO on 2018/3/21.
 */

public class LoginPresenter implements LoginPresenterInter {
    private LoginModel loginModel;
    private LoginActivityInter loginActivityInter;

    public LoginPresenter(LoginActivityInter loginActivityInter) {
        this.loginActivityInter = loginActivityInter;

        loginModel = new LoginModel(this);
    }

    //处理逻辑用的...判断
    public void getLogin(String loginUrl, String phone, String pwd) {

        loginModel.getLogin(loginUrl,phone,pwd);

    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        loginActivityInter.getLoginSuccess(loginBean);
    }

    @Override
    public void onSuccessByQQ(LoginBean loginBean, String ni_cheng, String iconurl) {
        loginActivityInter.getLoginSuccessByQQ(loginBean,ni_cheng,iconurl);
    }

    public void getLoginByQQ(String phone, String pwd, String ni_cheng, String iconurl) {

      // loginModel.getLoginByQQ(phone,pwd,ni_cheng,iconurl);

    }
}
