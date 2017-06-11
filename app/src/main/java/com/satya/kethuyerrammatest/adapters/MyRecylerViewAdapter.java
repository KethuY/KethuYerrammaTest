package com.satya.kethuyerrammatest.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.satya.kethuyerrammatest.R;
import com.satya.kethuyerrammatest.models.Video;

import java.util.List;

/**
 * Created by satya on 11-Jun-17.
 */

public class MyRecylerViewAdapter extends RecyclerView.Adapter<MyRecylerViewAdapter.MyViewHolder> {

    private List<Video> mVideoArrLst;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, time, desc;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title_tv);
            time = (TextView) view.findViewById(R.id.time_tv);
            desc = (TextView) view.findViewById(R.id.desc_tv);
            imageView = (ImageView) view.findViewById(R.id.image_view);
        }
    }


    public MyRecylerViewAdapter(List<Video> videoList) {
        this.mVideoArrLst = videoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Video video = mVideoArrLst.get(position);
        holder.title.setText(video.getTitle());
        holder.time.setText(video.getTime());
        holder.desc.setText(video.getDesc());
        holder.imageView.setBackgroundResource(video.getResouce());
    }

    @Override
    public int getItemCount() {
        return mVideoArrLst.size();
    }
}
