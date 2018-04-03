package bwie.com.jingdong.Presenter;

import bwie.com.jingdong.Model.MainModel_02;
import bwie.com.jingdong.Model.bean.Pruducts;
import bwie.com.jingdong.Presenter.i_presenter.IMain_Presenter02;
import bwie.com.jingdong.View.Iview.IMain_Activity_02;

/**
 * Created by LENOVO on 2018/3/22.
 */

public class MainPresenter_SouSuo implements IMain_Presenter02 {
    private IMain_Activity_02 iMain_activity_02;
    private MainModel_02 model02;

    public MainPresenter_SouSuo(IMain_Activity_02 iMain_activity_02) {
        this.iMain_activity_02 = iMain_activity_02;
        model02 = new MainModel_02(this);
    }
    //中间者中获取数据的方法
    public void getLoginData(String url_select,String keywords){
        model02.getLoginInModel(url_select,keywords);
    }

    @Override
    public void onSuccess(Pruducts pruducts) {
        iMain_activity_02.onSuccess(pruducts);
    }
}
