package com.example.gilbertpark.fourwheels;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Adapter.FilterCarAdpater;
import Data.CarData;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyPageActivity extends AppCompatActivity {

    @BindView(R.id.mypage_tv_car_size)
    TextView carSize;
    @BindView(R.id.mypage_tv_car_type)
    TextView carType;
    @BindViews({R.id.mypage_tv_car_size_1t, R.id.mypage_tv_car_size_1_4t, R.id.mypage_tv_car_size_2_5t, R.id.mypage_tv_car_size_3_5t, R.id.mypage_tv_car_size_5t, R.id.mypage_tv_car_size_11_15t, R.id.mypage_tv_car_size_18t, R.id.mypage_tv_car_size_25t})
    List<TextView> carSizeViews;
    @BindViews({R.id.mypage_tv_car_type_1, R.id.mypage_tv_car_type_2, R.id.mypage_tv_car_type_3, R.id.mypage_tv_car_type_4})
    List<TextView> carTypeViews;
    @BindView(R.id.mypage_rv)
    RecyclerView rv;

    @OnClick({R.id.mypage_tv_car_size, R.id.mypage_tv_car_type})
    public void OnClickSizeType(View v) {
        if (v.equals(carSize)) {
            carSize.setBackgroundResource(R.color.mainTab);
            carType.setBackgroundResource(R.color.mainTab_disable);

            int position = 2;
            carDatas.remove(position);
            adapter.notifyItemRemoved(position);

            for (TextView tv : carSizeViews) {
                tv.setVisibility(View.VISIBLE);
            }
            for (TextView tv : carTypeViews) {
                tv.setVisibility(View.GONE);
            }
        } else if (v.equals(carType)) {
            carSize.setBackgroundResource(R.color.mainTab_disable);
            carType.setBackgroundResource(R.color.mainTab);

            int position = 2;
            carDatas.set(position, new CarData("https://firebasestorage.googleapis.com/v0/b/findpcroom.appspot.com/o/dummy%2Fp4_1.jpg?alt=media"));
            adapter.notifyItemInserted(position);

            for (TextView tv : carSizeViews) {
                tv.setVisibility(View.GONE);
            }
            for (TextView tv : carTypeViews) {
                tv.setVisibility(View.VISIBLE);
            }
        }
    }

    @BindView(R.id.menu_iv_1)
    ImageView ivGotoMain;
    @BindView(R.id.menu_iv_2)
    ImageView ivGotoHistory;
    @BindView(R.id.menu_iv_3)
    ImageView ivGotoMyPage;
    @BindView(R.id.menu_iv_4)
    ImageView ivGotoCs;

    ArrayList<CarData> carDatas = new ArrayList<>();
    FilterCarAdpater adapter;

    @OnClick({R.id.menu_iv_1, R.id.menu_iv_2, R.id.menu_iv_3, R.id.menu_iv_4})
    public void OnClickMenu(View v) {
        if (v.equals(ivGotoMain)) {
            Intent intent = new Intent(MyPageActivity.this, MapViewer.class);
            startActivity(intent);
            finish();
        } else if (v.equals(ivGotoMyPage)) {

        } else if (v.equals(ivGotoHistory)) {
            Intent intent = new Intent(MyPageActivity.this, HistoryActivity.class);
            startActivity(intent);
            finish();
        } else if (v.equals(ivGotoCs)) {
            Intent intent = new Intent(MyPageActivity.this, CsActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        ButterKnife.bind(this);

        SetRv();
    }

    private void SetRv() {
        carDatas.add(new CarData("https://firebasestorage.googleapis.com/v0/b/findpcroom.appspot.com/o/dummy%2Fp1_1.jpg?alt=media"));
        carDatas.add(new CarData("https://firebasestorage.googleapis.com/v0/b/findpcroom.appspot.com/o/dummy%2Fp1_2.jpg?alt=media"));
        carDatas.add(new CarData("https://firebasestorage.googleapis.com/v0/b/findpcroom.appspot.com/o/dummy%2Fp1_3.jpg?alt=media"));
        carDatas.add(new CarData("https://firebasestorage.googleapis.com/v0/b/findpcroom.appspot.com/o/dummy%2Fp2_1.jpg?alt=media"));
        carDatas.add(new CarData("https://firebasestorage.googleapis.com/v0/b/findpcroom.appspot.com/o/dummy%2Fp2_2.jpg?alt=media"));
        carDatas.add(new CarData("https://firebasestorage.googleapis.com/v0/b/findpcroom.appspot.com/o/dummy%2Fp2_3.jpg?alt=media"));
        carDatas.add(new CarData("https://firebasestorage.googleapis.com/v0/b/findpcroom.appspot.com/o/dummy%2Fp3_1.jpg?alt=media"));
        carDatas.add(new CarData("https://firebasestorage.googleapis.com/v0/b/findpcroom.appspot.com/o/dummy%2Fp3_2.jpg?alt=media"));
        carDatas.add(new CarData("https://firebasestorage.googleapis.com/v0/b/findpcroom.appspot.com/o/dummy%2Fp3_3.jpg?alt=media"));
        carDatas.add(new CarData("https://firebasestorage.googleapis.com/v0/b/findpcroom.appspot.com/o/dummy%2Fp4_1.jpg?alt=media"));

        RecyclerView.LayoutManager lm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(lm);
        adapter = new FilterCarAdpater(this, carDatas);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyPageActivity.this, MapViewer.class);
        startActivity(intent);
        finish();
    }
}
