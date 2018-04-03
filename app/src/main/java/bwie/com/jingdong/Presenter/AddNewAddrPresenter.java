package bwie.com.jingdong.Presenter;

import bwie.com.jingdong.Model.AddNewAddrModel;
import bwie.com.jingdong.Model.bean.AddCartBean;
import bwie.com.jingdong.Model.bean.AddNewAddrBean;
import bwie.com.jingdong.Presenter.i_presenter.AddCartPresenterInter;
import bwie.com.jingdong.Presenter.i_presenter.AddNewAddrPresenterInter;
import bwie.com.jingdong.View.Iview.AddNewAddrInter;

/**
 * Created by LENOVO on 2018/3/23.
 */

public class AddNewAddrPresenter implements AddNewAddrPresenterInter {
    private AddNewAddrInter addNewAddrInter;
    private AddNewAddrModel addNewAddrModel;

    public AddNewAddrPresenter(AddNewAddrInter addNewAddrInter) {
        this.addNewAddrInter = addNewAddrInter;
        addNewAddrModel = new AddNewAddrModel(this);
    }

    public void addNewAddr(String addNewAddrUrl, String uid, String addr, String phone, String name) {

        addNewAddrModel.addNewAddr(addNewAddrUrl,uid,addr,phone,name);
    }




    @Override
    public void onAddAddrSuccess(AddNewAddrBean addNewAddrBean) {
        addNewAddrInter.onAddNewAddrSuccess(addNewAddrBean);
    }
}
