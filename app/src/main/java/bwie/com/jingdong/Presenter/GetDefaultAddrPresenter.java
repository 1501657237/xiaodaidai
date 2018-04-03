package bwie.com.jingdong.Presenter;

import bwie.com.jingdong.Model.GetDefaultAddrModel;
import bwie.com.jingdong.Model.bean.DefaultAddrBean;
import bwie.com.jingdong.Presenter.i_presenter.GetDefaultAddrPresenterInter;
import bwie.com.jingdong.View.Iview.DefaultAddrInter;
import bwie.com.jingdong.View.activity.MakeSureOrderActivity;

/**
 * Created by LENOVO on 2018/3/25.
 */

public class GetDefaultAddrPresenter implements GetDefaultAddrPresenterInter {
    private DefaultAddrInter defaultAddrInter;
    private GetDefaultAddrModel getDefaultAddrModel;

    public GetDefaultAddrPresenter(DefaultAddrInter defaultAddrInter) {
        this.defaultAddrInter = defaultAddrInter;
        getDefaultAddrModel = new GetDefaultAddrModel(this);
    }

    public void getDefaultAddr(String getDefaultAddrUrl, String uid) {
        getDefaultAddrModel.getDefaultAddr(getDefaultAddrUrl,uid);
    }

    @Override
    public void onGetDefaultAddrSuccess(DefaultAddrBean defaultAddrBean) {
        defaultAddrInter.onGetDefaultAddrSuccess(defaultAddrBean);
    }

    @Override
    public void onGetDefaultAddrEmpty() {
        defaultAddrInter.onGetDefaultAddrEmpty();
    }
}
