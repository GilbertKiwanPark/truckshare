package com.example.gilbertpark.fourwheels;

import android.Manifest;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoadingActivity extends AppCompatActivity {

    @BindView(R.id.loading_logo)
    ImageView logo;
    @BindView(R.id.login_layout)
    LinearLayout loginLayout;

    @BindView(R.id.loading_remember_check_box)
    ImageView checkbox;
    @BindView(R.id.loading_remember_check_box_padding)
    LinearLayout checkboxPadding;
    @BindView(R.id.loading_remember_check_box_tv)
    TextView checkboxTv;

    ProgressDialog loadingdialog;
    boolean isCheck = false;

    @OnClick({R.id.loading_remember_check_box, R.id.loading_remember_check_box_padding, R.id.loading_remember_check_box_tv})
    public void OnClick(View v) {
        if (v.equals(checkbox)) {
            isCheck = !isCheck;
            setCheck();
        } else if (v.equals(checkboxPadding)) {
            isCheck = !isCheck;
            setCheck();
        }
        if (v.equals(checkboxTv)) {
            isCheck = !isCheck;
            setCheck();
        }
    }

    private void setCheck() {
        if (isCheck) {
            checkbox.setImageResource(R.drawable.icons_check_on);
        } else {
            checkbox.setImageResource(R.drawable.icons_check_off);
        }
    }

    @BindView(R.id.loading_iv_login)
    ImageView ivLogin;
    @BindView(R.id.loading_iv_facebook_login)
    ImageView ivFacebookLogin;

    @OnClick({R.id.loading_iv_login, R.id.loading_iv_facebook_login})
    public void Login(View v) {
        if (v.equals(ivLogin)) {

        } else if (v.equals(ivFacebookLogin)) {
            LoginProgress loginProgress = new LoginProgress();
            loginProgress.execute();
        }
    }

    Animation animTransUp;
    Animation animAlpha700;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ButterKnife.bind(this);

        animTransUp = AnimationUtils.loadAnimation(this, R.anim.anim_transe_up);
        animAlpha700 = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_300);

        PermissionListening();
    }

    private void PermissionListening() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                WaitForViewLogo waitForViewLogo = new WaitForViewLogo();
                waitForViewLogo.execute();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(LoadingActivity.this, "권한에 동의하지 않으시면, 서비스를 이용하실 수 없습니다\n\n[설정]-[권한]에서 설정을 바꿀 수 있습니다", Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("FourWheels 의 서비스를 이용하기 위해서 다음 권한이 필요합니다.")
                .setDeniedMessage("권한에 동의하지 않으시면, 서비스를 이용하실 수 없습니다\n\n[설정]-[권한]에서 설정을 바꿀 수 있습니다")
                .setPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE)
                .check();
    }

    public class WaitForViewLogo extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            logo.startAnimation(animTransUp);

            loginLayout.setVisibility(View.VISIBLE);
            loginLayout.startAnimation(animAlpha700);

        }
    }

    public class LoginProgress extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingdialog = ProgressDialog.show(LoadingActivity.this, "", "로그인중입니다.", true);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadingdialog.dismiss();
        }
    }
}
