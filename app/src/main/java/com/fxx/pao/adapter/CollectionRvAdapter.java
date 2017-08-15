package com.fxx.pao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fxx.pao.R;
import com.fxx.pao.model.ArticleModel;
import com.fxx.pao.model.CollectionModel;
import com.fxx.pao.util.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 *
 * Created by fxx on 2017/8/10 0010.
 */

public class CollectionRvAdapter extends RecyclerView.Adapter<CollectionRvAdapter.MyViewHolder>{

    private List<CollectionModel.ItemsBean> mItems;
    private ItemClickListener mItemClickListener;

    public CollectionRvAdapter(List<CollectionModel.ItemsBean> items){
        mItems = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_collection,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTvTitle.setText(mItems.get(position).getTitle());
        holder.mTvNick.setText(mItems.get(position).getUser().getNickname());
        GlideUtil.loadHeadImage(mItems.get(position).getUser().getFace()).into(holder.mIvHeader);
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

    public List<CollectionModel.ItemsBean> getmItems() {
        return mItems;
    }

    public void setmItems(List<CollectionModel.ItemsBean> mItems) {
        this.mItems = mItems;
        this.notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.iv_head)
        CircleImageView mIvHeader;
        @BindView(R.id.tv_nick)
        TextView mTvNick;

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
