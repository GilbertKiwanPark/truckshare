package com.example.gilbertpark.fourwheels;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CsActivity extends AppCompatActivity {

    @BindView(R.id.menu_iv_1)
    ImageView ivGotoMain;
    @BindView(R.id.menu_iv_2)
    ImageView ivGotoHistory;
    @BindView(R.id.menu_iv_3)
    ImageView ivGotoMyPage;
    @BindView(R.id.menu_iv_4)
    ImageView ivGotoCs;

    @OnClick({R.id.menu_iv_1, R.id.menu_iv_2, R.id.menu_iv_3, R.id.menu_iv_4})
    public void OnClickMenu(View v) {
        if (v.equals(ivGotoMain)) {
            Intent intent = new Intent(CsActivity.this, MapViewer.class);
            startActivity(intent);
            finish();
        } else if (v.equals(ivGotoHistory)) {
            Intent intent = new Intent(CsActivity.this, HistoryActivity.class);
            startActivity(intent);
            finish();
        } else if (v.equals(ivGotoMyPage)) {
            Intent intent = new Intent(CsActivity.this, MyPageActivity.class);
            startActivity(intent);
            finish();
        } else if (v.equals(ivGotoCs)) {

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CsActivity.this, MapViewer.class);
        startActivity(intent);
        finish();
    }
}
