package bwie.com.jingdong.Presenter;

import bwie.com.jingdong.Model.CreateOrderBean;
import bwie.com.jingdong.Model.CreateOrderModel;
import bwie.com.jingdong.Presenter.i_presenter.CreateOrderPresenterInter;
import bwie.com.jingdong.View.Iview.CreateOrderInter;
import bwie.com.jingdong.View.activity.MakeSureOrderActivity;

/**
 * Created by LENOVO on 2018/3/25.
 */

public class CreateOrderPresenter implements CreateOrderPresenterInter {
    private CreateOrderInter createOrderInter;
    private CreateOrderModel createOrderModel;

    public CreateOrderPresenter(CreateOrderInter createOrderInter) {
        this.createOrderInter = createOrderInter;
        createOrderModel = new CreateOrderModel(this);
    }

    public void createOrder(String createOrderUrl, String uid, double price) {

        createOrderModel.createOrder(createOrderUrl,uid,price);

    }

    @Override
    public void onOrderCreateSuccess(CreateOrderBean createOrderBean) {
        createOrderInter.onCreateOrderSuccess(createOrderBean);
    }
}
