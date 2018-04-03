package bwie.com.jingdong.Presenter;

import android.util.Log;

import bwie.com.jingdong.Model.FragmentHomeModel;
import bwie.com.jingdong.Model.bean.FenLeiBean;
import bwie.com.jingdong.Model.bean.HomeBean;
import bwie.com.jingdong.Presenter.i_presenter.FragmentHomeP_Inter;
import bwie.com.jingdong.View.Iview.InterFragmentHome;

/**
 * Created by LENOVO on 2018/3/12.
 */

public class FragmentHomeP  implements FragmentHomeP_Inter {
    private FragmentHomeModel fragmentHomeModel;
    private InterFragmentHome interFragmentHome;

    public FragmentHomeP(InterFragmentHome interFragmentHome) {
        this.interFragmentHome = interFragmentHome;

        fragmentHomeModel=new FragmentHomeModel(this);
    }

    public void getNetData(String homeUrl) {

        //让model获取数据

        fragmentHomeModel.getData(homeUrl);

    }

    public void getFenLeiData(String fenLeiUrl) {

        fragmentHomeModel.getFenLeiData(fenLeiUrl);
    }


    @Override
    public void onSuccess(HomeBean homeBean) {
        //此时的数据回调到p层,,,把数据从p层传到view层进行使用
        interFragmentHome.onSuccess(homeBean);
    }

    @Override
    public void onFenLeiDataSuccess(FenLeiBean fenLeiBean) {
        interFragmentHome.onFenLeiDataSuccess(fenLeiBean);
    }
}
