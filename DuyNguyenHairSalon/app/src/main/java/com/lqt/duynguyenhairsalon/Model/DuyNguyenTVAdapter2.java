package com.lqt.duynguyenhairsalon.Model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lqt.duynguyenhairsalon.R;

import java.util.List;

/*
 * Đây là adapter ở trong Play List
 * */
public class DuyNguyenTVAdapter2  extends RecyclerView.Adapter<DuyNguyenTVAdapter2.DuyNguyenTVPlayListViewHolder>{

    List<VideoYouTube> playList;
    Context context;

    public DuyNguyenTVAdapter2(Context context) {
        this.context = context;
    }

    public void setData(List<VideoYouTube> playList) {
        this.playList = playList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DuyNguyenTVPlayListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_play_list_video_youtube
        ,parent
        ,false);

        return new DuyNguyenTVPlayListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DuyNguyenTVPlayListViewHolder holder, int position) {
        VideoYouTube videoYouTube = playList.get(position);
        if (videoYouTube == null){
            return;
        }

        Glide.with(context).load(videoYouTube.getThumbnail()).into(holder.imageViewThum);
        holder.textViewTitle.setText(videoYouTube.getTitle());
    }

    @Override
    public int getItemCount() {
        if(playList != null){
            return playList.size();
        }
        return 0;
    }

    public class DuyNguyenTVPlayListViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewThum;
        TextView textViewTitle;

        public DuyNguyenTVPlayListViewHolder(@NonNull View itemView) {
            super(itemView);
             imageViewThum = (ImageView) itemView.findViewById(R.id.imageView_Thumnail) ;
             textViewTitle = (TextView) itemView.findViewById(R.id.textView_Title);
        }
    }
}
