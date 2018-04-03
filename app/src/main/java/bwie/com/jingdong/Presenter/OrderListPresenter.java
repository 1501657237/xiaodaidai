package bwie.com.jingdong.Presenter;

import bwie.com.jingdong.Model.OrderListBean;
import bwie.com.jingdong.Model.OrderListModel;
import bwie.com.jingdong.Presenter.i_presenter.OrderListPresenterInter;
import bwie.com.jingdong.View.Iview.FragmentOrderListInter;

/**
 * Created by LENOVO on 2018/3/25.
 */

public class OrderListPresenter  implements OrderListPresenterInter {
    private FragmentOrderListInter fragmentOrderListInter;
    private OrderListModel orderListModel;

    public OrderListPresenter(FragmentOrderListInter fragmentOrderListInter) {
        this.fragmentOrderListInter = fragmentOrderListInter;
        orderListModel = new OrderListModel(this);
    }

    public void getOrderData(String orderListUrl, String uid, int page) {

        orderListModel.getOrderData(orderListUrl,uid,page);

    }

    @Override
    public void onOrderDataSuccess(OrderListBean orderListBean) {

        fragmentOrderListInter.onOrderDataSuccess(orderListBean);
    }
}
