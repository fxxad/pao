package com.fxx.pao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fxx.pao.R;
import com.fxx.pao.model.CommentModel;
import com.fxx.pao.util.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 *  评论列表适配器
 * Created by fxx on 2017/8/10 0010.
 */

public class CommentRvAdapter extends RecyclerView.Adapter<CommentRvAdapter.MyViewHolder>{

    private List<CommentModel.ItemsBean> mItems;
    private ItemClickListener mItemClickListener;

    public CommentRvAdapter(List<CommentModel.ItemsBean> items){
        mItems = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_rv,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GlideUtil.loadHeadImage(mItems.get(position).getUser().getFace()).into(holder.mIvhead);
        holder.mTvNick.setText(mItems.get(position).getUser().getNickname());
        holder.mTVDate.setText(mItems.get(position).getDtime());
        holder.mTvContent.setText(mItems.get(position).getMsg());
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

    public List<CommentModel.ItemsBean> getmItems() {
        return mItems;
    }

    public void setmItems(List<CommentModel.ItemsBean> mItems) {
        this.mItems = mItems;
        this.notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_head)
        CircleImageView mIvhead;
        @BindView(R.id.tv_nick)
        TextView mTvNick;
        @BindView(R.id.tv_date)
        TextView mTVDate;
        @BindView(R.id.tv_comment_content)
        TextView mTvContent;

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
