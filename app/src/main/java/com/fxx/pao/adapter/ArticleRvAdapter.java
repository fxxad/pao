package com.fxx.pao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fxx.pao.R;
import com.fxx.pao.model.ArticleModel;
import com.fxx.pao.util.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 *  RecyclerView文章列表适配器
 * Created by fxx on 2017/8/10 0010.
 */

public class ArticleRvAdapter extends RecyclerView.Adapter<ArticleRvAdapter.MyViewHolder>{

    private List<ArticleModel.ItemsBean> mItems;
    private ItemClickListener mItemClickListener;

    public ArticleRvAdapter(List<ArticleModel.ItemsBean> items){
        mItems = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_rv,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTvTitle.setText(mItems.get(position).getTitle());
        holder.mTvLileNum.setText(String.valueOf(mItems.get(position).getStow()));
        holder.mTvCommentNum.setText(String.valueOf(mItems.get(position).getComments()));
        holder.mTvDate.setText(mItems.get(position).getPubDate());
        holder.mTvNick.setText(mItems.get(position).getUser().getNickname());
        holder.mTvPraiseNum.setText(String.valueOf(mItems.get(position).getUpvote()));
        holder.mTvReadNum.setText(String.valueOf(mItems.get(position).getClick()));
        if(mItems.get(position).getThumbnail()== null || mItems.get(position).getThumbnail().equals("")){
            holder.mIvThumb.setVisibility(View.INVISIBLE);
        }else{
            holder.mIvThumb.setVisibility(View.VISIBLE);
            GlideUtil.loadDefaultImage(mItems.get(position).getThumbnail()).into(holder.mIvThumb);
        }
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

    public List<ArticleModel.ItemsBean> getmItems() {
        return mItems;
    }

    public void setmItems(List<ArticleModel.ItemsBean> mItems) {
        this.mItems = mItems;
        this.notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_likenum)
        TextView mTvLileNum;
        @BindView(R.id.tv_comment_num)
        TextView mTvCommentNum;
        @BindView(R.id.tv_date)
        TextView mTvDate;
        @BindView(R.id.iv_thumb)
        ImageView mIvThumb;
        @BindView(R.id.iv_header)
        CircleImageView mIvHeader;
        @BindView(R.id.tv_nick)
        TextView mTvNick;
        @BindView(R.id.tv_praise_num)
        TextView mTvPraiseNum;
        @BindView(R.id.tv_read_num)
        TextView mTvReadNum;

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
