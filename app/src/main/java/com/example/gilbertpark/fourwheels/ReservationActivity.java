package com.example.gilbertpark.fourwheels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReservationActivity extends AppCompatActivity {

    @BindView(R.id.reservation_iv_back)
    ImageView backBtn;

    @OnClick(R.id.reservation_iv_back)
    public void OnClick(View v) {
        if (v.equals(backBtn)) {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
