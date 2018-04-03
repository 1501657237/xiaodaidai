package bwie.com.jingdong.Presenter;

import android.content.Context;

import java.util.List;

import bwie.com.jingdong.Model.GetProvinceModel;
import bwie.com.jingdong.Model.bean.ProvinceBean;
import bwie.com.jingdong.Presenter.i_presenter.GetProvincePresenterInter;
import bwie.com.jingdong.View.Iview.GetProvinceInter;

/**
 * Created by LENOVO on 2018/3/24.
 */

public class GetProvincePresenter implements GetProvincePresenterInter {
    private GetProvinceInter getProvinceInter;
    private GetProvinceModel getProvinceModel;

    public GetProvincePresenter(GetProvinceInter getProvinceInter) {
        this.getProvinceInter = getProvinceInter;
        getProvinceModel = new GetProvinceModel(this);
    }

    public void getProvince(Context context) {
        getProvinceModel.getProvince(context);
    }

    @Override
    public void onGetProvince(List<ProvinceBean> list) {
        getProvinceInter.onGetProvince(list);
    }
}
