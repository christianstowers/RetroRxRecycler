package com.christianstowers.retro_rx_recycler.ui.androidListActivity;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.christianstowers.retro_rx_recycler.R;
import com.christianstowers.retro_rx_recycler.data.entities.AndroidModel;

import java.util.ArrayList;

public class AndroidListAdapter extends RecyclerView.Adapter<AndroidListAdapter.MyViewHolder> {

    //pass AndroidModel model class as an ArrayList in the constructor
    private ArrayList<AndroidModel> mAndroidList;

    public AndroidListAdapter(ArrayList<AndroidModel> androidList) {
        mAndroidList = androidList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        // obtain the Android version name, version, and API level from the
        // Android object using getter methods and set it to TextView widgets.
        holder.mTvName.setText(mAndroidList.get(position).getName());
        holder.mTvVersion.setText(mAndroidList.get(position).getVer());
        holder.mTvApi.setText(mAndroidList.get(position).getApi());
    }

    @Override
    public int getItemCount() {
        return mAndroidList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName, mTvVersion, mTvApi;
        public MyViewHolder(View view) {
            super(view);

            mTvName = (TextView)view.findViewById(R.id.tv_name);
            mTvVersion = (TextView)view.findViewById(R.id.tv_version);
            mTvApi = (TextView)view.findViewById(R.id.tv_api_level);
        }
    }

}
