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
import com.fxx.pao.view.BannerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 *  综合资讯适配器
 * Created by fxx on 2017/8/10 0010.
 */

public class SyntheticalRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int VIEW_TYPE_BANNER=0;
    private static final int VIEW_TYPE_LIST=1;

    private List<ArticleModel.ItemsBean> mItems;
    private ItemClickListener mItemClickListener;
    private List<ArticleModel.ItemsBean> mBannerItems;

    public SyntheticalRvAdapter(List<ArticleModel.ItemsBean> items){
        mItems = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_BANNER){
            return new BannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live_banner,parent,false));
        }else{
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_rv,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            position -= 1;
            MyViewHolder viewHolder =(MyViewHolder)holder;
            viewHolder.mTvTitle.setText(mItems.get(position).getTitle());
            viewHolder.mTvLileNum.setText(String.valueOf(mItems.get(position).getStow()));
            viewHolder.mTvCommentNum.setText(String.valueOf(mItems.get(position).getComments()));
            viewHolder.mTvDate.setText(mItems.get(position).getPubDate());
            viewHolder.mTvNick.setText(mItems.get(position).getUser().getNickname());
            viewHolder.mTvPraiseNum.setText(String.valueOf(mItems.get(position).getUpvote()));
            viewHolder.mTvReadNum.setText(String.valueOf(mItems.get(position).getClick()));
            GlideUtil.loadDefaultImage(mItems.get(position).getThumbnail()).into(viewHolder.mIvThumb);
            GlideUtil.loadHeadImage(mItems.get(position).getUser().getFace()).into(viewHolder.mIvHeader);
            setClickListener(((MyViewHolder)holder),position);
        }else if(holder instanceof BannerViewHolder){
            ((BannerViewHolder)holder).mBannerView
                    .delayTime(5)
                    .build(mBannerItems);
        }
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
        if(mBannerItems==null || mBannerItems.size()<=0){
            return mItems.size();
        }else{
            return mItems.size()+1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return VIEW_TYPE_BANNER;
        }else{
            return VIEW_TYPE_LIST;
        }
//        return super.getItemViewType(position);
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

    static class BannerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_live_banner)
        BannerView mBannerView;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public List<ArticleModel.ItemsBean> getmBannerItems() {
        return mBannerItems;
    }

    public void setmBannerItems(List<ArticleModel.ItemsBean> mBannerItems) {
        this.mBannerItems = mBannerItems;
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
