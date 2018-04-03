package bwie.com.jingdong.Model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bwie.com.jingdong.Model.bean.DeatilBean;
import bwie.com.jingdong.Presenter.i_presenter.DeatilPresenterInter;
import bwie.com.jingdong.util.OkHttp3Util_03;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by LENOVO on 2018/3/14.
 */

public class DeatilModel {
    private DeatilPresenterInter deatilPresenterInter;

    public DeatilModel(DeatilPresenterInter deatilPresenterInter) {
        this.deatilPresenterInter = deatilPresenterInter;
    }
    public void getDetailData(String detailUrl, int pid) {
        Map<String, String> params=new HashMap<>();
        params.put("pid", String.valueOf(pid));
        OkHttp3Util_03.doPost(detailUrl, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();

                    //解析
                    final DeatilBean deatilBean = new Gson().fromJson(json,DeatilBean.class);

                    deatilPresenterInter.onSuccess(deatilBean);

                }
            }
        });
    }
}
