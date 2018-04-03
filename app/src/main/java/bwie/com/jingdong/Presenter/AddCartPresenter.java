package bwie.com.jingdong.Presenter;

import bwie.com.jingdong.Model.AddCartModel;
import bwie.com.jingdong.Model.bean.AddCartBean;
import bwie.com.jingdong.Presenter.i_presenter.AddCartPresenterInter;
import bwie.com.jingdong.View.Iview.ActivityAddCartInter;
import bwie.com.jingdong.View.activity.DetailActivity;

/**
 * Created by LENOVO on 2018/3/21.
 */

public class AddCartPresenter implements AddCartPresenterInter {
    private ActivityAddCartInter activityAddCartInter;
    private AddCartModel addCartModel;

    public AddCartPresenter(ActivityAddCartInter activityAddCartInter) {
        this.activityAddCartInter = activityAddCartInter;

        addCartModel = new AddCartModel(this);
    }

    public void addToCart(String addCart, String uid, int pid) {

        addCartModel.addToCart(addCart,uid,pid);

    }

    @Override
    public void onCartAddSuccess(AddCartBean addCartBean) {
        activityAddCartInter.onCartAddSuccess(addCartBean);
    }
}
