package bwie.com.jingdong.Presenter;

import java.io.File;

import bwie.com.jingdong.Model.UpLoadPicModel;
import bwie.com.jingdong.Model.bean.UpLoadPicBean;
import bwie.com.jingdong.Presenter.i_presenter.UpLoadPicPresenterInter;
import bwie.com.jingdong.View.Iview.UpLoadActivityInter;
import bwie.com.jingdong.View.activity.UserInfoActivity;

/**
 * Created by LENOVO on 2018/3/23.
 */

public class UpLoadPicPresenter implements UpLoadPicPresenterInter {
    private UpLoadPicModel upLoadPicModel;
    private UpLoadActivityInter upLoadActivityInter;

    public UpLoadPicPresenter(UpLoadActivityInter upLoadActivityInter) {
        this.upLoadActivityInter = upLoadActivityInter;
        upLoadPicModel = new UpLoadPicModel(this);
    }

    public void uploadPic(String uploadIconUrl, File saveIconFile, String uid, String fileName) {

        upLoadPicModel.uploadPic(uploadIconUrl,saveIconFile,uid,fileName);

    }

    @Override
    public void uploadPicSuccess(UpLoadPicBean upLoadPicBean) {
        upLoadActivityInter.uploadPicSuccess(upLoadPicBean);
    }
}
