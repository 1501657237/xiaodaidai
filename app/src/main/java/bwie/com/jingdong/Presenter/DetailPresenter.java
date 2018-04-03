package bwie.com.jingdong.Presenter;

import bwie.com.jingdong.Model.DeatilModel;
import bwie.com.jingdong.Model.bean.DeatilBean;
import bwie.com.jingdong.Presenter.i_presenter.DeatilPresenterInter;
import bwie.com.jingdong.Presenter.i_presenter.DetailActivityInter;
import bwie.com.jingdong.View.activity.DetailActivity;

/**
 * Created by LENOVO on 2018/3/14.
 */

public class DetailPresenter implements DeatilPresenterInter {
    private DeatilModel deatilModel;
    private DetailActivityInter detailActivityInter;

    public DetailPresenter(DetailActivityInter detailActivityInter) {
        this.detailActivityInter = detailActivityInter;

        deatilModel = new DeatilModel(this);

    }
    public void getDetailData(String detailUrl, int pid) {

        deatilModel.getDetailData(detailUrl,pid);
    }

    @Override
    public void onSuccess(DeatilBean deatilBean) {
        //回调给view
        detailActivityInter.onSuccess(deatilBean);
    }
}
