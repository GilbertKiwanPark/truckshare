package com.example.gilbertpark.fourwheels;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReservationActivity extends AppCompatActivity {

    @BindView(R.id.reservation_iv_main)
    ImageView ivBody;
    @BindView(R.id.reservation_iv_back)
    ImageView backBtn;
    @BindView(R.id.btn_booking)
    ImageView btnBooking;

    @OnClick(R.id.reservation_iv_back)
    public void OnClick(View v) {
        if (v.equals(backBtn)) {
            finish();
        }
    }

    @OnClick(R.id.btn_booking)
    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogStyle);

        builder.setMessage(Html.fromHtml("<font color='#8a000000'>Is the payment information accurate?</font><br>" +
                "<font color='#8a000000'>If you click [YES], your reservation and payment will proceed.</font>"));
        builder.setPositiveButton(Html.fromHtml("<font color='#009688'>Yes</font>"), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                btnBooking.setVisibility(View.GONE);
                ivBody.setImageResource(R.drawable.booking_page_detail);
            }
        });
        builder.setNegativeButton(Html.fromHtml("<font color=#009688'>No</font>"), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

            }
        });

        TextView title = new TextView(this);
        title.setText("Booking Confirmation");
        title.setTextColor(getResources().getColor(R.color.colorBlack));
        title.setTextSize(16f);
        title.setGravity(Gravity.CENTER);
        title.setPadding(20, 50, 20, 20);
        builder.setCustomTitle(title);

        builder.create();
        AlertDialog dialog = builder.show();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.height = 700;
        lp.width = 800;
        dialog.getWindow().setAttributes(lp);

        TextView message = dialog.findViewById(android.R.id.message);
        message.setGravity(Gravity.CENTER);
        message.setTextSize(14f);

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
