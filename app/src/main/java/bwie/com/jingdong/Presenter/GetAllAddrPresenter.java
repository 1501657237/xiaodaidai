package bwie.com.jingdong.Presenter;

import bwie.com.jingdong.Model.GetAllAddrModel;
import bwie.com.jingdong.Model.bean.GetAllAddrBean;
import bwie.com.jingdong.Presenter.i_presenter.GetAllAddrPresenterInter;
import bwie.com.jingdong.View.Iview.GetAllAddrInter;

/**
 * Created by LENOVO on 2018/3/23.
 */

public class GetAllAddrPresenter implements GetAllAddrPresenterInter {
    private GetAllAddrInter getAllAddrInter;
    private GetAllAddrModel getAllAddrModel;

    public GetAllAddrPresenter(GetAllAddrInter getAllAddrInter) {
        this.getAllAddrInter = getAllAddrInter;
        getAllAddrModel = new GetAllAddrModel(this);
    }

    public void getAllAddr(String getAllAddrUrl, String uid) {
        getAllAddrModel.getAllAddr(getAllAddrUrl,uid);
    }

    @Override
    public void onGetAllAddrSuccess(GetAllAddrBean getAllAddrBean) {
        getAllAddrInter.onGetAllAddrSuccess(getAllAddrBean);
    }
}
