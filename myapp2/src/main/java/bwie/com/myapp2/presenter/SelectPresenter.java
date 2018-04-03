package bwie.com.myapp2.presenter;


import bwie.com.myapp2.model.SelectModel;
import bwie.com.myapp2.view.IView.SelectMain;
import bwie.com.myapp2.view.bean.GoodsBean;

/**
 * Created by ASUS on 2018/3/27.
 */

public class SelectPresenter implements Selectpre {

    private SelectMain selectMain;
    private SelectModel selectModel;

    public SelectPresenter(SelectMain selectMain) {
        this.selectMain=selectMain;
        selectModel = new SelectModel(this);
    }

    public void getDate(String url) {
        selectModel.getDateUrl(url);
    }

    @Override
    public void OnSuccess(GoodsBean goodsBean) {
        selectMain.SelectSuccess(goodsBean);
    }
}
