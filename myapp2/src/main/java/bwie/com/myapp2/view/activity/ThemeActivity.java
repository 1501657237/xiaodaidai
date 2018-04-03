package bwie.com.myapp2.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.View;


import bwie.com.myapp2.R;
import bwie.com.myapp2.util.StatusUtil;
import skin.support.SkinCompatManager;

public class ThemeActivity extends BaseActivity implements View.OnClickListener {
    private View mView;
    private CardView mCardViewGreen;
    private CardView mCardViewBlue;
    private CardView mCardViewYellow;
    private CardView mCardViewPick;
    private CardView mCardViewPurple;
    private CardView mCardViewRed;

    @Override
    protected void initOperation(Intent intent) {
        mCardViewGreen.setOnClickListener(this);
        mCardViewBlue.setOnClickListener(this);
        mCardViewYellow.setOnClickListener(this);
        mCardViewPick.setOnClickListener(this);
        mCardViewPurple.setOnClickListener(this);
        mCardViewRed.setOnClickListener(this);
    }

    @Override
    protected View addContentView() {
        mView = View.inflate(this, R.layout.activity_theme, null);
        mCardViewGreen = (CardView) mView.findViewById(R.id.card_green);
        mCardViewBlue = (CardView) mView.findViewById(R.id.card_blue);
        mCardViewYellow = (CardView) mView.findViewById(R.id.card_yellow);
        mCardViewPick = (CardView) mView.findViewById(R.id.card_pick);
        mCardViewPurple = (CardView) mView.findViewById(R.id.card_purple);
        mCardViewRed = (CardView) mView.findViewById(R.id.card_red);
        return mView;
    }

    @Override
    protected String setToolbarTitle() {
        return "切换主题";
    }
    @Override
    protected void updateOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_download).setVisible(true);
        menu.findItem(R.id.action_save).setVisible(true);
        menu.findItem(R.id.action_share).setVisible(true);
    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.card_green:
                    SkinCompatManager.getInstance().restoreDefaultTheme();
                    break;
                case R.id.card_blue:
//                    SkinCompatManager.getInstance().loadSkin("skinblue.skin", null);
                    StatusUtil.setWindowTitleColor(ThemeActivity.this,ThemeActivity.this,"#5082B4");
                    break;
                case R.id.card_yellow:
//                    SkinCompatManager.getInstance().loadSkin("skinyellow.skin", null);
                    StatusUtil.setWindowTitleColor(ThemeActivity.this,ThemeActivity.this,"#F9C134");
                    break;
                case R.id.card_pick:
//                    SkinCompatManager.getInstance().loadSkin("skinpick.skin", null);
                    StatusUtil.setWindowTitleColor(ThemeActivity.this,ThemeActivity.this,"#FCBCB8");
                    break;
                case R.id.card_purple:
//                    SkinCompatManager.getInstance().loadSkin("skinpurple.skin", null);
                    StatusUtil.setWindowTitleColor(ThemeActivity.this,ThemeActivity.this,"#9579BB");
                    break;
                case R.id.card_red:
//                    SkinCompatManager.getInstance().loadSkin("skinred.skin", null);
                    StatusUtil.setWindowTitleColor(ThemeActivity.this,ThemeActivity.this,"#FC6453");
                    break;
            }
    }
}
