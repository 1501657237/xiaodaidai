package bwie.com.myapp2.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import bwie.com.myapp2.R;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class TiaoZhuan extends AppCompatActivity {

    private ImageView mIv;
    private TextView mTvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiao_zhuan);
        mIv = (ImageView) findViewById(R.id.image_guide);
        mTvTime = (TextView) findViewById(R.id.tv_time);
        Glide.with(this)
                .load(R.drawable.guide_bg)
                // 设置高斯模糊
                .bitmapTransform(new BlurTransformation(this,25,4))
                .into(mIv);
        new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTvTime.setText(millisUntilFinished / 1000 + " s");
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onFinish() {
                setupWindowAnimations();
                startActivity(new Intent(TiaoZhuan.this, MainActivity.class));
                finish();
            }
        }.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(5000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(fade);
        }
    }
}
