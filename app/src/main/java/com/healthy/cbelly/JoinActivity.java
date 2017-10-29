package com.healthy.cbelly;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JoinActivity extends BaseActivity {

    @Bind(R.id.toolbar_btn)
    Button toolbarBtn;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.id)
    EditText id;
    @Bind(R.id.pw)
    EditText pw;
    @Bind(R.id.pwcon)
    EditText pwcon;
    @Bind(R.id.birth)
    EditText birth;
    @Bind(R.id.city)
    EditText city;
    @Bind(R.id.center)
    EditText center;
    @Bind(R.id.weight)
    EditText weight;
    @Bind(R.id.tall)
    EditText tall;
    @Bind(R.id.waist)
    EditText waist;
    @Bind(R.id.weghit_kg)
    TextView weghitKg;
    @Bind(R.id.photo)
    ImageView photo;

    @Bind(R.id.cancel)
    Button cancel;
    @Bind(R.id.confirm)
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        ButterKnife.bind(this);

        initToolbar(toolbar);

        // TODO 레이아웃 작업 끝남 코드 작성
    }

    @OnClick({R.id.toolbar_btn, R.id.confirm, R.id.cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_btn:
                finish();
                break;
            case R.id.confirm:
                break;
            case R.id.cancel:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
