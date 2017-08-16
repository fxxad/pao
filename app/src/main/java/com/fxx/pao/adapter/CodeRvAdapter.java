package com.fxx.pao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fxx.pao.R;
import com.fxx.pao.model.CodeModel;
import com.fxx.pao.util.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *RecyclerView代码列表适配器
 * Created by fxx on 2017/8/10 0010.
 */

public class CodeRvAdapter extends RecyclerView.Adapter<CodeRvAdapter.MyViewHolder>{

    private List<CodeModel.ItemsBean> mItems;
    private ItemClickListener mItemClickListener;

    public CodeRvAdapter(List<CodeModel.ItemsBean> items){
        mItems = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_code_rv,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTvTitle.setText(mItems.get(position).getTitle());
        holder.mTvDesc.setText(mItems.get(position).getDescribe());
        holder.mTvLileNum.setText(String.valueOf(mItems.get(position).getStow()));
        holder.mTvVisiNum.setText(String.valueOf(mItems.get(position).getClick()));
        holder.mTvDate.setText(mItems.get(position).getPubDate());
        GlideUtil.loadDefaultImage(mItems.get(position).getThumbnail()).into(holder.mIvThumb);
        setClickListener(holder,position);
    }

    private void setClickListener(MyViewHolder holder, final int position){
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener != null)
                    mItemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public List<CodeModel.ItemsBean> getmItems() {
        return mItems;
    }

    public void setmItems(List<CodeModel.ItemsBean> mItems) {
        this.mItems = mItems;
        this.notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_desc)
        TextView mTvDesc;
        @BindView(R.id.tv_likenum)
        TextView mTvLileNum;
        @BindView(R.id.tv_visinum)
        TextView mTvVisiNum;
        @BindView(R.id.tv_date)
        TextView mTvDate;
        @BindView(R.id.iv_thumb)
        ImageView mIvThumb;

        View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            ButterKnife.bind(this,itemView);
        }
    }

    public ItemClickListener getmItemClickListener() {
        return mItemClickListener;
    }

    public void setmItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface ItemClickListener{
        void onClick(int position);
    }
}
