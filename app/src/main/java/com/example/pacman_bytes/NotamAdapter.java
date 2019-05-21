package com.example.pacman_bytes;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NotamAdapter extends RecyclerView.Adapter<NotamAdapter.NotamView> {
    Context mContext;
    ArrayList<NotamData> mNotamData;
    RecyclerView rs;

    public NotamAdapter(){
        mNotamData = new ArrayList<>();
        mContext = null;

    }

    public NotamAdapter(Context c,ArrayList<NotamData> n,RecyclerView rs){
        mContext = c;
        mNotamData = n;
        this.rs = rs;
    }

    @NonNull
    @Override
    public NotamView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.notam_card,viewGroup,false);
        return new NotamView(v);
    }
    int mExpanded = -1;


    @Override
    public void onBindViewHolder(@NonNull final NotamView notamView, final int i) {
        notamView.mSubarea.setText(mNotamData.get(i).getmSubarea());
        notamView.mArea.setText(mNotamData.get(i).getmArea());
        notamView.mSubject.setText(mNotamData.get(i).getmSubject());
        final boolean isExpanded = i == mExpanded;
        if(isExpanded) {
            notamView.mRawNotam.setVisibility(View.VISIBLE);
            notamView.mRawNotam.setText(mNotamData.get(i).getmNotam());
        }else{
            notamView.mRawNotam.setVisibility(View.GONE);
        }

        notamView.mDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpanded = isExpanded? -1:i;
                TransitionManager.beginDelayedTransition(rs);
                notifyDataSetChanged();
            }
        });

    }


    void deleteItem(int position) {
        mNotamData.remove(position);
        notifyItemRemoved(position);


    }


    @Override
    public int getItemCount() {
        return mNotamData.size();
    }

    class NotamView extends RecyclerView.ViewHolder{
        TextView mSubarea,mArea,mSubject,mRawNotam;
        ImageView mDrop;


         NotamView(@NonNull View itemView) {
            super(itemView);
            mSubarea = itemView.findViewById(R.id.subarea);
            mArea = itemView.findViewById(R.id.area);
            mSubject  = itemView.findViewById(R.id.subject);
            mRawNotam = itemView.findViewById(R.id.raw_notam);
            mDrop = itemView.findViewById(R.id.drop_down);
        }
    }
}
