package com.healthy.cbelly;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.healthy.cbelly.util.MaterialAlertDialogUtil;
import com.healthy.cbelly.util.SuperToast;

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

        // TODO 코드 작성
    }

    @OnClick({R.id.login, R.id.join, R.id.find_id, R.id.find_pw,
            R.id.toolbar_btn, R.id.toolbar_title})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.login:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.join:
                intent = new Intent(this, JoinActivity.class);
                startActivity(intent);
                break;
            case R.id.find_id:
                findInfoAlert("아이디 찾기", "생년월일을 - 없이 입력하세요.", "", title_id);
                break;
            case R.id.find_pw:
                findInfoAlert("비밀번호 찾기", "생년월일을 - 없이 입력하세요.", "", title_pw);
                break;
        }
    }

    private String message;
    private String title_id = "아이디";
    private String title_pw = "패스워드";

    public void findInfoAlert(String title, String hint, String prefill, final String title_result) {
        MaterialAlertDialogUtil.inputAlert(this, title, hint, prefill,
                new MaterialAlertDialogUtil.InputListner() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        message = input.toString();
                        if(TextUtils.isEmpty(message)) {
                            SuperToast.show(mContext, "생년월일을 입력하세요.");
                        }else {
                            String title;
                            String msg;
                            if(title_result.equalsIgnoreCase(title_id)) {
                                title = title_id;
                                msg = "cbelly@gmail.com";
                            }else {
                                title = title_pw;
                                msg = "1234";
                            }
                           MaterialAlertDialogUtil.confirmAlert(mContext, title, msg, false, "확인",
                                   new MaterialAlertDialogUtil.OnPositiveListner() {
                                       @Override
                                       public void onPositive(@NonNull MaterialDialog dialog, DialogAction which) {
                                           dialog.dismiss();
                                       }
                                   }).show();
                        }
                    }
                }, new MaterialAlertDialogUtil.OnPositiveListner() {
                    @Override
                    public void onPositive(@NonNull MaterialDialog dialog, DialogAction which) {
                        dialog.dismiss();
                    }
                }, new MaterialAlertDialogUtil.OnNegativeListner() {
                    @Override
                    public void onNegative(@NonNull MaterialDialog dialog, DialogAction which) {
                        dialog.dismiss();
                    }
                }).show();
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
