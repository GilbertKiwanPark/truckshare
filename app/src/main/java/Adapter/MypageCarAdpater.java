package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.gilbertpark.fourwheels.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Data.CarData;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MypageCarAdpater extends RecyclerView.Adapter<MypageCarAdpater.ViewHolder> {

    Context context;
    ArrayList<CarData> datas;

    public MypageCarAdpater(Context context, ArrayList<CarData> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mypage_car_adapter, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MypageCarAdpater.ViewHolder holder, int position) {
        final CarData data = datas.get(position);
        Picasso.get().load(data.getImg_url()).into(holder.carImage);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv)
        public ImageView carImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

//    public void delete(int position) {
//
//        try {
//            mDataSet.remove(position);
//            notifyItemRemoved(position);
//        } catch (IndexOutOfBoundsException ex) {
//            ex.printStackTrace();
//        }
//
//    }
//
//
//    public void add(int position, String infoData) {
//        mDataSet.add(position, infoData);
//        notifyItemInserted(position);
//    }
}