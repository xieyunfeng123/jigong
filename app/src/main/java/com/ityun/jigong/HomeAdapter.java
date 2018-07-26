package com.ityun.jigong;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/23 0023.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyHolder> {

    private Context context;

    private List<Person> mlist;

    public HomeAdapter(Context context) {
        this.context = context;
    }


    public void setData(List<Person> mlist) {
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
       holder.item_user_name.setText(mlist.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return null != mlist ? mlist.size() : 0;
    }


    class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_user_name)
        public TextView item_user_name;

        @BindView(R.id.item_user_jobTime)
        public TextView item_user_jobTime;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
