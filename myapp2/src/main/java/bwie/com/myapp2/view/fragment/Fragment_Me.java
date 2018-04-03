package bwie.com.myapp2.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.leon.lib.settingview.LSettingItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import bwie.com.myapp2.R;
import bwie.com.myapp2.view.activity.MainActivity;
import bwie.com.myapp2.view.activity.MyBlog;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by ASUS on 2018/3/23.
 */

public class Fragment_Me extends Fragment implements View.OnClickListener {

    private LSettingItem mSettingItemBlog;
    private LSettingItem mSettingItemFollow;
    private LSettingItem mSettingItemSave;
    private LSettingItem mSettingItemVersion;
    private TextView mTvNickName;
    private CircleImageView mIvHeader;
    private MainActivity mActivity;
    private String name;
    private PopupWindow popupWindow;
    private TextView text_camera;
    private TextView text_local_pic;
    private TextView text_cancel;
    private String pic_path = Environment.getExternalStorageDirectory() + "/head.jpg";
    //裁剪完成之后图片保存的路径
    private String crop_icon_path = Environment.getExternalStorageDirectory() + "/head_icon.jpg";
    private View parent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        mSettingItemBlog = (LSettingItem) view.findViewById(R.id.item_blog);
        mSettingItemFollow = (LSettingItem) view.findViewById(R.id.item_follow);
        mSettingItemSave = (LSettingItem) view.findViewById(R.id.item_save);
        mSettingItemVersion = (LSettingItem) view.findViewById(R.id.item_version);
        mTvNickName = (TextView) view.findViewById(R.id.tv_nickname);
        mIvHeader = (CircleImageView) view.findViewById(R.id.iv_header);
        initPopupWindown();
        return view;
    }

    private void initPopupWindown() {
        parent = View.inflate(getActivity(), R.layout.fragment_me, null);

        View contentView = View.inflate(getActivity(), R.layout.upload_pop_layout, null);

        //第一个参数展示的popupwindown的视图,第二个参数宽度,第三参数是高度..
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //设置
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);//外边可以触摸
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        //找到控件
        text_camera = contentView.findViewById(R.id.text_camera);
        text_local_pic = contentView.findViewById(R.id.text_local_pic);
        text_cancel = contentView.findViewById(R.id.text_cancel);

        //设置点击事件
        text_camera.setOnClickListener(this);
        text_local_pic.setOnClickListener(this);
        text_cancel.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences preferences = getActivity().getSharedPreferences("name", 0);
        name = preferences.getString("mName", "公共参数");
        final String mBlog = preferences.getString("mBlog", "mBlog");
        final String mOther = preferences.getString("mOther", "mOther");
        mSettingItemBlog.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                Intent intent = new Intent(mActivity, MyBlog.class);
                intent.putExtra("url", mBlog);
                intent.putExtra("isfromme", true);
                startActivity(intent);
            }
        });

        mSettingItemVersion.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                Toast.makeText(mActivity, "已经是最新版本了", Toast.LENGTH_SHORT).show();
            }
        });
        mSettingItemFollow.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                Toast.makeText(mActivity, "正在开发中", Toast.LENGTH_SHORT).show();
            }
        });
        mSettingItemSave.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                Toast.makeText(mActivity, "正在开发中", Toast.LENGTH_SHORT).show();
            }
        });
        mIvHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mTvNickName.setText(name);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_camera://拍照

                paiZhao();

                popupWindow.dismiss();
                break;
            case R.id.text_local_pic://相册

                getLocalPic();

                popupWindow.dismiss();
                break;
            case R.id.text_cancel://取消

                popupWindow.dismiss();
                break;
        }
    }

    private void getLocalPic() {
        Intent intent = new Intent();
        //指定选择/获取的动作...PICK获取,拿
        intent.setAction(Intent.ACTION_PICK);
        //指定获取的数据的类型
        intent.setType("image/*");

        startActivityForResult(intent, 3000);
    }

    private void paiZhao() {
        Intent intent = new Intent();
        //指定动作...拍照的动作 CAPTURE...捕获
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        //给相机传递一个指令,,,告诉他拍照之后保存..MediaStore.EXTRA_OUTPUT向外输出的指令,,,指定存放的位置
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(pic_path)));

        //拍照的目的是拿到拍的图片
        startActivityForResult(intent, 1000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            Uri uri = Uri.fromFile(new File(pic_path));
            //拍照保存之后进行裁剪....根据图片的uri路径
            crop(uri);
            mIvHeader.setImageURI(uri);
        }

        //获取相册图片
        if (requestCode == 3000 && resultCode == RESULT_OK) {
            //获取的是相册里面某一张图片的uri地址
            Uri uri = data.getData();

            //根据这个uri地址进行裁剪
            crop(uri);
            mIvHeader.setImageURI(uri);
        }

        if (requestCode == 2000 && resultCode == RESULT_OK) {
            //获取到裁剪完的图片
            Bitmap bitmap = data.getParcelableExtra("data");

            //拿到了bitmap图片 ..需要把bitmap图片压缩保存到文件中去..应该去做上传到服务器的操作了
            File saveIconFile = new File(crop_icon_path);

            if (saveIconFile.exists()) {
                saveIconFile.delete();
            }

            try {
                //创建出新的文件
                saveIconFile.createNewFile();

                FileOutputStream fos = new FileOutputStream(saveIconFile);
                //把bitmap通过流的形式压缩到文件中
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void crop(Uri uri) {
        Intent intent = new Intent();

        //指定裁剪的动作
        intent.setAction("com.android.camera.action.CROP");

        //设置裁剪的数据(uri路径)....裁剪的类型(image/*)
        intent.setDataAndType(uri, "image/*");

        //执行裁剪的指令
        intent.putExtra("crop", "true");
        //指定裁剪框的宽高比
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        //指定输出的时候宽度和高度
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);

        //设置取消人脸识别
        intent.putExtra("noFaceDetection", false);
        //设置返回数据
        intent.putExtra("return-data", true);

        //
        startActivityForResult(intent, 2000);
    }
}
