package bwie.com.jingdong.Presenter;

import bwie.com.jingdong.Model.RegistModel;
import bwie.com.jingdong.Model.bean.RegistBean;
import bwie.com.jingdong.Presenter.i_presenter.RegistPresenterInter;
import bwie.com.jingdong.View.Iview.RegistActivityInter;
import bwie.com.jingdong.View.activity.RegistActivity;

/**
 * Created by LENOVO on 2018/3/21.
 */

public class RegistPresenter implements RegistPresenterInter {
    private RegistActivityInter registActivityInter;
    private RegistModel registModel;

    public RegistPresenter(RegistActivityInter registActivityInter) {
        this.registActivityInter = registActivityInter;
        registModel = new RegistModel(this);
    }

    public void registUser(String registUrl, String name, String pwd) {

        registModel.registUser(registUrl,name,pwd);
    }

    @Override
    public void onRegistSuccess(RegistBean registBean) {
        registActivityInter.onRegistSuccess(registBean);
    }
}
