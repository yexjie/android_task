package com.example.AndroidTask.MainFram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cq_1014_task.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.LinearViewHolder> {
    private Context mContext;
    private OnItemClickListener mListener;


    private List<String> list;

    //    public LinearAdapter(Context context, List<String> list){
//        this.mContext=context;
//        this.list = list;
//    }
    public MyAdapter(Context context, OnItemClickListener listener){
        this.mContext=context;
        this.mListener=listener;
        //this.list = list;
    }

    @Override
    public MyAdapter.LinearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.homepage_fragment_recycleitem,parent,false));//传入view，即每一个item长什么样的布局
    }

    @Override
    public void onBindViewHolder(MyAdapter.LinearViewHolder holder, final int position) {
        holder.title.setText("标题");
        holder.time.setText("2021/10/14");
        holder.content.setText("内容");
        holder.imag.setImageResource(R.drawable.news_picture);

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }


    class LinearViewHolder extends RecyclerView.ViewHolder{

        private TextView title,time,content;
        private ImageView imag;

        public LinearViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.tv_ti);
            time=itemView.findViewById(R.id.tv_time);
            content=itemView.findViewById(R.id.tv_content);
            imag=itemView.findViewById(R.id.newspic);
        }
    }

    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
