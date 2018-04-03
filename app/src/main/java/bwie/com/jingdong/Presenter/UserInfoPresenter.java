package bwie.com.jingdong.Presenter;

import bwie.com.jingdong.Model.UserInfoModel;
import bwie.com.jingdong.Model.bean.UserInfoBean;
import bwie.com.jingdong.Presenter.i_presenter.UserInfoPresenterInter;
import bwie.com.jingdong.View.Iview.UserInforInter;

/**
 * Created by LENOVO on 2018/3/23.
 */

public class UserInfoPresenter implements UserInfoPresenterInter {
    private final UserInfoModel userInfoModel;
    private final UserInforInter userInforInter;

    public UserInfoPresenter(UserInforInter userInforInter) {
        this.userInforInter = userInforInter;
        userInfoModel = new UserInfoModel(this);
    }

    public void getUserInfo(String userInfoUrl, String uid) {

        userInfoModel.getUserInfo(userInfoUrl,uid);

    }

    @Override
    public void onUserInfoSUccess(UserInfoBean userInfoBean) {
        userInforInter.onUserInforSuccess(userInfoBean);
    }
}
