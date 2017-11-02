package com.healthy.cbelly;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.healthy.cbelly.util.LogUtil;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar_btn)
    Button toolbarBtn;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.user_photo)
    ImageView userPhoto;
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.user_weight)
    TextView userWeight;
    @Bind(R.id.user_waist)
    TextView userWaist;
    @Bind(R.id.user_bmi)
    TextView userBmi;
    @Bind(R.id.user_pulse)
    TextView userPulse;

    @Bind(R.id.user_healthy)
    Button userHealthy;
    @Bind(R.id.user_contents)
    Button userContents;
    @Bind(R.id.user_history)
    Button userHistory;
    @Bind(R.id.user_analyze)
    Button userAnalyze;

    private Float bmi;
    private int pulse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_main);
        ButterKnife.bind(this);



        String t1 = loginAccount.tall.substring(0,1);
        LogUtil.e("Main",t1);
        String t2 = loginAccount.tall.substring(1, loginAccount.tall.length());
        LogUtil.e("Main",t2);
        String t3 = t1 + "." + t2;
        LogUtil.e("Main",t3);
        float tall = Float.valueOf(t3);

        int weight = Integer.valueOf(loginAccount.weight);

        bmi = (weight / (tall * tall));
        LogUtil.e("MAIN",""+bmi);



        Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), loginAccount.photo));
        LogUtil.e("MAIN", uri.toString());
        userPhoto.setImageURI(uri);
        userName.setText(loginAccount.name);
        userWeight.setText(loginAccount.weight);
        userWaist.setText(loginAccount.waist);
        userBmi.setText(String.format("%.2f", bmi));
        userPulse.setText("70 - 125");
    }

    @OnClick({R.id.user_healthy, R.id.user_contents, R.id.user_history, R.id.user_analyze})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_healthy:
                break;
            case R.id.user_contents:
                break;
            case R.id.user_history:
                break;
            case R.id.user_analyze:
                break;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
