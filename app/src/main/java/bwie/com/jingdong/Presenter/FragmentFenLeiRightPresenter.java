package bwie.com.jingdong.Presenter;

import bwie.com.jingdong.Model.FragmentFenLeiRightModel;
import bwie.com.jingdong.Model.bean.ChildFenLeiBean;
import bwie.com.jingdong.Presenter.i_presenter.FenLeiRightPresenterInter;
import bwie.com.jingdong.View.Iview.FenLeiRightInter;

/**
 * Created by LENOVO on 2018/3/14.
 */

public class FragmentFenLeiRightPresenter  implements FenLeiRightPresenterInter {

    private FenLeiRightInter fenLeiRightInter;
    private FragmentFenLeiRightModel fragmentFenLeiRightModel;

    public FragmentFenLeiRightPresenter(FenLeiRightInter fenLeiRightInter) {
        this.fenLeiRightInter = fenLeiRightInter;

        fragmentFenLeiRightModel = new FragmentFenLeiRightModel(this);
    }

    public void getChildData(String childFenLeiUrl, int cid) {
        fragmentFenLeiRightModel.getChildData(childFenLeiUrl,cid);
    }

    @Override
    public void getSuccessChildData(ChildFenLeiBean childFenLeiBean) {

        fenLeiRightInter.getSuccessChildData(childFenLeiBean);
    }
}
