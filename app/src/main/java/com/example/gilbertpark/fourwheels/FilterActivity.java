package com.example.gilbertpark.fourwheels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import Adapter.FilterCarAdpater;
import Adapter.FilterTabAdpater;
import Data.CarData;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterActivity extends AppCompatActivity {

    @BindView(R.id.filter_tab_1)
    TextView tabCapacity;
    @BindView(R.id.filter_tab_2)
    TextView tabType;
    @BindView(R.id.filter_item_rv)
    RecyclerView itemRv;
    @BindView(R.id.filter_truck_rv)
    RecyclerView carRv;
    @BindView(R.id.filer_iv_next)
    ImageView next;

    @OnClick(R.id.filer_iv_next)
    public void OnNext(View v) {
        if(v.equals(next)) {
            finish();
        }
    }

    @OnClick({R.id.filter_tab_1, R.id.filter_tab_2})
    public void OnClickTab(View v) {
        if (v.equals(tabCapacity)) {
            tabCapacity.setBackgroundResource(R.drawable.upper_tab_act);
            tabType.setBackgroundResource(R.drawable.upper_tab_deact);
            setTabRv(capacityList);
        } else if (v.equals(tabType)) {
            tabType.setBackgroundResource(R.drawable.upper_tab_act);
            tabCapacity.setBackgroundResource(R.drawable.upper_tab_deact);
            setTabRv(typeList);
        }
    }

    ArrayList<String> capacityList = new ArrayList<>();
    ArrayList<String> typeList = new ArrayList<>();
    ArrayList<CarData> orgCardata = new ArrayList<>();
    ArrayList<CarData> carData = new ArrayList<>();

    public ArrayList<String> setList = new ArrayList<>();

    FilterTabAdpater tabAdapter;
    FilterCarAdpater carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        capacityList.add("1t");
        capacityList.add("1.4t");
        capacityList.add("2.5t");
        capacityList.add("3.5t");
        capacityList.add("5t");
        capacityList.add("11-15t");
        capacityList.add("18t");
        capacityList.add("25t");

        typeList.add("일반 카고");
        typeList.add("탑차 윙바디");
        typeList.add("플러스 축차 카고");
        typeList.add("플러스 축차 탑차 윙바디");

        orgCardata.add(new CarData("1.4t", "포드", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/1ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("2.5t", "포드", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("5t", "포드", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/11ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("25t", "포드", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/25ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("1t", "포드", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/1ton%20topwingtop.png?alt=media"));
        orgCardata.add(new CarData("2.5t", "포드", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("5t", "포드", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/11ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("25t", "포드", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("5t", "포드", "플러스 축차 카고 ,", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/11ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("18t", "포드", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/18ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("18t", "포드", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/18ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("1.4t", "포드", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/1ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("1t", "포드", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/1ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("2.5t", "포드", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("3.5t", "포드", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("5t", "포드", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/11ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("11t", "포드", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/11ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("25t", "포드", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/25ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("1t", "포드", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/1ton%20topwingtop.png?alt=media"));
        orgCardata.add(new CarData("1.4t", "포드", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/1ton%20topwingtop.png?alt=media"));
        orgCardata.add(new CarData("2.5t", "포드", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("18t", "도요타", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/18ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("3.5t", "포드", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("5t", "포드", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/11ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("11t", "포드", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/11ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("25t", "포드", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("5t", "포드", "플러스 축차 탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("5t", "포드", "플러스 축차 카고 ,", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/11ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("18t", "포드", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/18ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("18t", "포드", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/18ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("1.4t", "현대", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/1ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("1t", "현대", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/1ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("2.5t", "현대", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("3.5t", "현대", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("5t", "현대", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/11ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("11t", "현대", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/11ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("25t", "현대", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/25ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("1t", "현대", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/1ton%20topwingtop.png?alt=media"));
        orgCardata.add(new CarData("1.4t", "현대", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/1ton%20topwingtop.png?alt=media"));
        orgCardata.add(new CarData("2.5t", "현대", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("3.5t", "현대", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("5t", "현대", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/11ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("11t", "현대", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/11ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("25t", "현대", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("5t", "현대", "플러스 축차 탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("5t", "현대", "플러스 축차 카고 ,", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/11ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("18t", "현대", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/18ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("18t", "현대", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/18ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("1.4t", "도요타", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/1ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("1t", "도요타", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/1ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("2.5t", "도요타", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("3.5t", "도요타", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("5t", "도요타", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/11ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("11t", "도요타", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/11ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("25t", "도요타", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/25ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("1t", "도요타", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/1ton%20topwingtop.png?alt=media"));
        orgCardata.add(new CarData("1.4t", "도요타", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/1ton%20topwingtop.png?alt=media"));
        orgCardata.add(new CarData("2.5t", "도요타", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("3.5t", "도요타", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("5t", "도요타", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/11ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("11t", "도요타", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/11ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("25t", "도요타", "일반카고 , ", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("5t", "도요타", "플러스 축차 탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/3.5ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("5t", "도요타", "플러스 축차 카고 ,", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/11ton%20cargo.png?alt=media"));
        orgCardata.add(new CarData("18t", "도요타", "탑차 윙바디", "https://firebasestorage.googleapis.com/v0/b/wheels-7fa40.appspot.com/o/18ton%20cargo.png?alt=media"));

        setTabRv(capacityList);
        setCarRv(null);
    }

    private void setTabRv(ArrayList<String> list) {
        RecyclerView.LayoutManager lm;
        if (list.size() > 4) {
            lm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        } else {
            lm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        }
        itemRv.setLayoutManager(lm);
        tabAdapter = new FilterTabAdpater(this, list);
        itemRv.setAdapter(tabAdapter);
        tabAdapter.notifyDataSetChanged();
    }

    public void setCarRv(ArrayList<String> list) {

        if (list == null) {
            carData = orgCardata;
        } else {
            carData = new ArrayList<>();
            for (CarData data : orgCardata) {
                if (list.contains(data.getCapacity()) || list.contains(data.getCar_type())) {
                    carData.add(data);
                }
            }
        }

        RecyclerView.LayoutManager lm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        carRv.setLayoutManager(lm);
        carAdapter = new FilterCarAdpater(this, carData);
        carRv.setAdapter(carAdapter);
        carAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
