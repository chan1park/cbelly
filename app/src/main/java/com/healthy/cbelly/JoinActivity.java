package com.healthy.cbelly;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.healthy.cbelly.account.JoinInfo;
import com.healthy.cbelly.util.LogUtil;
import com.healthy.cbelly.util.SuperToast;

import java.io.File;

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
    @Bind(R.id.target_weight)
    EditText target_weight;
    @Bind(R.id.photo)
    ImageView photo;

    @Bind(R.id.cancel)
    Button cancel;
    @Bind(R.id.confirm)
    Button confirm;

    private JoinInfo joinInfo;

    private Uri mImageCaptureUri;
    private Uri mCropUri;

    private boolean isCamera;

    private String imageFileName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        ButterKnife.bind(this);

        initToolbar(toolbar);

        joinInfo = new JoinInfo();

    }

    @OnClick({R.id.toolbar_btn, R.id.confirm, R.id.cancel, R.id.photo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_btn:
                finish();
                break;
            case R.id.confirm:
                joinInfo.name = getText(name);
                joinInfo.id = getText(id);
                joinInfo.pw = getText(pw);
                joinInfo.birth = getText(birth);
                joinInfo.address = getText(city);
                joinInfo.center = getText(center);
                joinInfo.weight = getText(weight);
                joinInfo.tall = getText(tall);
                joinInfo.waist = getText(waist);
                joinInfo.target_weight = getText(target_weight);

                emptyCheck(joinInfo);
                break;
            case R.id.cancel:
                finish();
                break;
            case R.id.photo:
                View v = LayoutInflater.from(mContext).inflate(R.layout.image_alert_layout, null, false);
                final MaterialDialog.Builder builder = new MaterialDialog.Builder(mContext);
                builder.title("사진 불러오기")
                        .customView(v, false);
                final MaterialDialog dialog = builder.build();
                dialog.show();
                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.camera:
//                                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                                startActivityForResult(i,1);

                                isCamera = true;
//                                String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
//                                mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
//                                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                                camera.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
//                                startActivityForResult(camera, 0);

                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
                                startActivityForResult(intent, 0);

                                dialog.dismiss();
                                break;
                            case R.id.from_photo:
//                                Intent intent = new Intent(Intent.ACTION_PICK);
//                                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                                intent.setType("image/*");
//                                startActivityForResult(Intent.createChooser(intent, "사진 선택"), 2);

                                isCamera = false;
                                Intent photo = new Intent(Intent.ACTION_PICK);
                                photo.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                                startActivityForResult(photo, 1);
                                dialog.dismiss();
                                break;
                        }
                    }
                };
                Button btn1 = (Button) v.findViewById(R.id.camera);
                btn1.setOnClickListener(listener);
                Button btn2 = (Button) v.findViewById(R.id.from_photo);
                btn2.setOnClickListener(listener);
                break;
        }
    }

    private void emptyCheck(JoinInfo vo) {
        String msg = "";
//        if (isEmpty(vo.name)) {
//            msg = "이름";
//        } else if (isEmpty(vo.id)) {
//            msg = "아이디";
//        } else if (isEmpty(vo.pw)) {
//            msg = "패스워드";
//        } else if (isEmpty(vo.birth)) {
//            msg = "생년월일";
//        } else if (isEmpty(vo.address)) {
//            msg = "주소";
//        } else if (isEmpty(vo.center)) {
//            msg = "소속 센터";
//        } else if (isEmpty(vo.weight)) {
//            msg = "체중";
//        } else if (isEmpty(vo.tall)) {
//            msg = "키";
//        } else if (isEmpty(vo.waist)) {
//            msg = "허리둘레";
//        } else if (isEmpty(vo.target_weight)) {
//            msg = "목표 체중";
//        } else {
//            msg = "";
//        }

        if (!TextUtils.isEmpty(msg)) {
            SuperToast.show(mContext, msg + " 를(을) 입력해주세요.");
            return;
        } else {
            regex(vo);
        }
    }

    private void regex(JoinInfo vo) {
        String msg = "";
//        if (!RegExpression.regexKorean(vo.name)) {
//            msg = "한글만 입력 가능합니다.";
//            name.requestFocus();
//        } else if (!RegExpression.regexEmail(vo.id)) {
//            msg = "이메일 형식만 입력 가능합니다.";
//            id.requestFocus();
//        } else if (!RegExpression.regexNumber(vo.pw)) {
//            msg = "비밀번호는 숫자만 입력 가능합니다.";
//            pw.requestFocus();
//        } else if (!RegExpression.regexNumber(vo.birth)) {
//            msg = "생년월일은 숫자만 입력 가능합니다.";
//            birth.requestFocus();
//        } else {
//            msg = "";
//        }

        if (!TextUtils.isEmpty(msg)) {
            SuperToast.show(mContext, msg);
            return;
        } else {
            eqaulsPassword(vo);
        }
    }

    private void eqaulsPassword(JoinInfo vo) {
        String pw1 = pw.getText().toString();
        String pw2 = pwcon.getText().toString();
        if (!pw1.equals(pw2)) {
            SuperToast.show(mContext, "비밀번호 입력이 확인과 다릅니다.");
            return;
        } else {
            insertMemberInfo(vo);
            finish();
        }
    }

    private void insertMemberInfo(JoinInfo vo) {
        if (!TextUtils.isEmpty(imageFileName)) {
            vo.photo = imageFileName;
        } else {
            vo.photo = "empty";
        }
        dbOpenHelper.insertColumn(vo.name, vo.id, vo.pw, vo.birth, vo.address, vo.center,
                vo.weight, vo.tall, vo.waist, vo.target_weight, vo.photo);

        doWhileCursorToArray();

        SuperToast.show(mContext,"가입이 완료 되었습니다.");
    }

    private String getText(EditText editText) {
        return editText.getText().toString();
    }

    private boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
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

    private File getTempFile() {
        imageFileName = String.valueOf(System.currentTimeMillis()) + "_crop.jpg";
        File file = new File(Environment.getExternalStorageDirectory(), imageFileName);
        try {
            file.createNewFile();
        } catch (Exception e) {
            Log.e("cklee", "fileCreation fail");
        }
        return file;
    }

    private Uri getJustTakenPictureUri() {
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
        if (cursor == null) return null;

        String fileName = null;
        if (cursor.moveToLast())
            fileName = cursor.getString(0);
        cursor.close();

        if (TextUtils.isEmpty(fileName)) return null;
        return Uri.fromFile(new File(fileName));
    }

    private static final String TYPE_IMAGE = "image/*";
    private Uri mTempImageUri;

    private void doCrop() {
        Uri justTakenPictureUri = getJustTakenPictureUri();
        mTempImageUri = Uri.fromFile(getTempFile());
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(justTakenPictureUri, TYPE_IMAGE);
        intent.putExtra("outputX", 200); // crop한 이미지의 x축 크기
        intent.putExtra("outputY", 200); // crop한 이미지의 y축 크기
        intent.putExtra("aspectX", 1); // crop 박스의 x축 비율
        intent.putExtra("aspectY", 1); // crop 박스의 y축 비율
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mTempImageUri);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        if (null != data) {
            switch (requestCode) {
                case 1:
                    mImageCaptureUri = data.getData();
                case 0:
                    if (isCamera) {
                        doCrop();
                    } else {
                        Intent intent = new Intent("com.android.camera.action.CROP");
                        intent.setDataAndType(mImageCaptureUri, "image/*");

                        // crop한 이미지를 저장할때 200x200 크기로 저장
                        intent.putExtra("outputX", 200); // crop한 이미지의 x축 크기
                        intent.putExtra("outputY", 200); // crop한 이미지의 y축 크기
                        intent.putExtra("aspectX", 1); // crop 박스의 x축 비율
                        intent.putExtra("aspectY", 1); // crop 박스의 y축 비율
                        intent.putExtra("scale", true);
                        intent.putExtra("return-data", true);
                        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

                        imageFileName = System.currentTimeMillis() + "capture.jpg";
                        mCropUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), imageFileName));
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, mCropUri);
                        startActivityForResult(intent, 2);
                    }
                    break;
                case 2:
                    if (isCamera) {
                        File tempFile2 = new File(Environment.getExternalStorageDirectory(), imageFileName);
                        try {
                            tempFile2.createNewFile();
                        } catch (Exception e) {

                        }
                        if (tempFile2.exists())
                            photo.setImageBitmap(BitmapFactory.decodeFile(tempFile2.toString()));
                    }else {
                        Bitmap cropPhoto = BitmapFactory.decodeFile(mCropUri.getPath());
                        photo.setImageBitmap(cropPhoto);
                    }
                    break;
            }
        }
    }
}
