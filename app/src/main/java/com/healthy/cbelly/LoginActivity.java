package com.healthy.cbelly;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.healthy.cbelly.account.LoginAccount;
import com.healthy.cbelly.database.DB;
import com.healthy.cbelly.util.LogUtil;
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

    private Vibrator mViberator;

    private LoginAccount account;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initToolbar(toolbar);

        toolbarBtn.setVisibility(View.GONE);

        cameraPermissioinCheck();

        mViberator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        account = new LoginAccount();

        initDB(this);

        id.setText("1@1.com");
        pw.setText("0000");
    }

    private void cameraPermissioinCheck() {

        // BLE 사용자 권한 체크
        int camera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int read = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);


        /*권한이 없는 경우에 요청*/
        if (PackageManager.PERMISSION_DENIED == camera) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    0); // 마지막 인자값은 콜백에서 구분할 요청 코드
        } else if (PackageManager.PERMISSION_DENIED == read) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1); // 마지막 인자값은 콜백에서 구분할 요청 코드
        } else {

        }
    }

    private String msg;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        msg = "";
        switch (requestCode) {
            case 0:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    msg = "카메라";
                }
                break;
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    msg = "저장소";
                }
                break;
        }
        if (!TextUtils.isEmpty(msg)) {
            MaterialAlertDialogUtil.confirmAlert(mContext, "권한 요청 실패", msg + " 권한이 없는 경우 사용 할 수 없습니다.\n확인을 누르면 앱이 종료 됩니다.", false,
                    "확인", new MaterialAlertDialogUtil.OnPositiveListner() {
                        @Override
                        public void onPositive(@NonNull MaterialDialog dialog, DialogAction which) {
                            dialog.dismiss();
                            finish();
                        }
                    }).show();
        }
    }

    @OnClick({R.id.login, R.id.join, R.id.find_id, R.id.find_pw,
            R.id.toolbar_btn, R.id.toolbar_title})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.login:

                id.setError(null);
                pw.setError(null);

                String user_id = id.getText().toString().trim();
                String user_pw = pw.getText().toString().trim();
                if (TextUtils.isEmpty(user_id)) {
                    id.requestFocus();
                    id.setError("아이디를 입력해주세요.");
//                    mViberator.vibrate(500);
                } else if (TextUtils.isEmpty(user_pw)) {
                    pw.requestFocus();
                    pw.setError("패스워드를 입력해주세요.");
//                    mViberator.vibrate(500);
                } else {
                    account = getMemberInfo(user_id);
                    LogUtil.d("LoginActivity", account.toString());
                    if (!user_id.equals(account.id) || !user_pw.equals(account.pw)) {
                        MaterialAlertDialogUtil.confirmAlert(mContext, "로그인", "아이디 또는 패스워드가 일치하지 않습니다.", false, "확인",
                                new MaterialAlertDialogUtil.OnPositiveListner() {
                                    @Override
                                    public void onPositive(@NonNull MaterialDialog dialog, DialogAction which) {
                                        dialog.dismiss();
                                    }
                                }).show();
                    } else {
                        setLoginaAccount(account);
                        SuperToast.show(mContext, "로그인 되었습니다.");
                        intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    }
                }
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

    private LoginAccount getMemberInfo(String id) {
        LogUtil.d("LoginActivity", id);
        cursor = null;
        cursor = dbOpenHelper.getMatchID(id);
        LoginAccount account = new LoginAccount();
        while (cursor.moveToNext()) {
            account.colum = cursor.getInt(cursor.getColumnIndex("_id"));
            account.name = cursor.getString(cursor.getColumnIndex(DB.CreateDB.NAME));
            account.id = cursor.getString(cursor.getColumnIndex(DB.CreateDB.ID));
            account.pw = cursor.getString(cursor.getColumnIndex(DB.CreateDB.PW));
            account.birth = cursor.getString(cursor.getColumnIndex(DB.CreateDB.BIRTH));
            account.address = cursor.getString(cursor.getColumnIndex(DB.CreateDB.ADDRESS));
            account.center = cursor.getString(cursor.getColumnIndex(DB.CreateDB.CENTER));
            account.weight = cursor.getString(cursor.getColumnIndex(DB.CreateDB.WEIGHT));
            account.tall = cursor.getString(cursor.getColumnIndex(DB.CreateDB.TALL));
            account.waist = cursor.getString(cursor.getColumnIndex(DB.CreateDB.WAIST));
            account.target_weight = cursor.getString(cursor.getColumnIndex(DB.CreateDB.TARGET_WEIGHT));
            account.photo = cursor.getString(cursor.getColumnIndex(DB.CreateDB.PHOTO));
        }
        cursor.close();
        return account;
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
                        if (TextUtils.isEmpty(message)) {
                            SuperToast.show(mContext, "생년월일을 입력하세요.");
                        } else {
                            String title;
                            String msg;
                            if (title_result.equalsIgnoreCase(title_id)) {
                                title = title_id;
                                if (findDB(message)) {
                                    msg = mName + " 회원님의 아이디는 " + mId + " 입니다.";
                                    id.setText(mId);
                                } else {
                                    msg = result;
                                }
                            } else {
                                title = title_pw;
                                if(findDB(message)) {
                                    msg = mName + " 회원님의 패스워드는 " + mPass + " 입니다.";
                                    pw.setText(mPass);
                                }else {
                                    msg = result;
                                }
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

    String mName = "";
    String mId = "";
    String mPass = "";
    String br = "";
    String result;

    public boolean findDB(String birth) {
        cursor = null;
        cursor = dbOpenHelper.getMatchBirth(birth);
        while (cursor.moveToNext()) {
            mName = cursor.getString(cursor.getColumnIndex(DB.CreateDB.NAME));
            mId = cursor.getString(cursor.getColumnIndex(DB.CreateDB.ID));
            mPass = cursor.getString(cursor.getColumnIndex(DB.CreateDB.PW));
            br = cursor.getString(cursor.getColumnIndex(DB.CreateDB.BIRTH));
        }
        cursor.close();

        if (birth.equals(br)) {
            result = "";
            return true;
        } else {
            result = "일치하는 회원 정보가 없습니다.";
            return false;
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
