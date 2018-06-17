package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gilbertpark.fourwheels.FilterActivity;
import com.example.gilbertpark.fourwheels.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Data.CarData;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterTabAdpater extends RecyclerView.Adapter<FilterTabAdpater.ViewHolder> {

    Context context;
    ArrayList<String> datas;
    ArrayList<String> parentList = new ArrayList();

    public FilterTabAdpater(Context context, ArrayList<String> datas) {
        this.context = context;
        this.datas = datas;
        parentList = ((FilterActivity) context).setList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_filter_tab, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FilterTabAdpater.ViewHolder holder, int position) {
        final String data = datas.get(position);
        holder.tv.setText(data);

        Log.e("data ", data+"|"+parentList.contains(data));
        if (parentList.contains(data)) {
            holder.root.setBackgroundResource(R.drawable.buttons_select);
        } else {
            holder.root.setBackgroundResource(R.drawable.buttons_deselect);
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.filter_tab_root)
        public LinearLayout root;
        @BindView(R.id.filter_tab_text)
        public TextView tv;

        @OnClick(R.id.filter_tab_root)
        public void OnClick(View v) {
            if (v.equals(root)) {
                parentList = ((FilterActivity) context).setList;

                if (parentList.contains(datas.get(getAdapterPosition()))) {
                    for (int i = 0; i < parentList.size(); i++) {
                        if (parentList.get(i).equals(datas.get(getAdapterPosition()))) {
                            parentList.remove(i);
                            return;
                        }
                    }
                } else {
                    parentList.add(datas.get(getAdapterPosition()));
                }
                notifyDataSetChanged();
            }
        }

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}