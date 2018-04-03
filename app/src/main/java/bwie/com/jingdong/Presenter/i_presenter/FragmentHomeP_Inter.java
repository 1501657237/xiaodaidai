package bwie.com.jingdong.Presenter.i_presenter;

import bwie.com.jingdong.Model.bean.FenLeiBean;
import bwie.com.jingdong.Model.bean.HomeBean;

/**
 * Created by LENOVO on 2018/3/12.
 */

public interface FragmentHomeP_Inter {
    //首页的数据
    void onSuccess(HomeBean homeBean);
    //分类
    void onFenLeiDataSuccess(FenLeiBean fenLeiBean);
}
