package com.healthy.cbelly;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chan1park on 2017. 10. 24..
 */

public class LoginActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_btn)
    Button toolbarBtn;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.logo)
    ImageView logo;
    @Bind(R.id.id)
    EditText id;
    @Bind(R.id.pw)
    EditText pw;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.join)
    Button join;
    @Bind(R.id.find_id)
    Button findId;
    @Bind(R.id.find_pw)
    Button findPw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initToolbar(toolbar);

        toolbarBtn.setVisibility(View.GONE);
    }

    @OnClick({R.id.login, R.id.join, R.id.find_id, R.id.find_pw,
            R.id.toolbar_btn, R.id.toolbar_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                break;
            case R.id.join:
                Intent intent = new Intent(this, JoinActivity.class);
                startActivity(intent);
                break;
            case R.id.find_id:
                break;
            case R.id.find_pw:
                break;
        }
    }

    public void checkLoginRegex(String id, String pw) {

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
