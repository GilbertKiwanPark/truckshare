package com.example.gilbertpark.fourwheels;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ButterKnife.bind(this);

        PermissionListening();
    }

    private void PermissionListening() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Intent intent = new Intent(LoadingActivity.this, MapViewer.class);
                startActivity(intent);
                finish();
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
}
