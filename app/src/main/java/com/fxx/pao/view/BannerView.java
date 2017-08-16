package com.fxx.pao.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fxx.pao.R;
import com.fxx.pao.model.ArticleModel;
import com.fxx.pao.ui.article.articledetail.ArticleDetailActivity;
import com.fxx.pao.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Banner轮播控件
 */
public class BannerView extends RelativeLayout
        implements BannerAdapter.ViewPagerOnItemClickListener {

    @BindView(R.id.layout_banner_viewpager)
    ViewPager viewPager;

    @BindView(R.id.layout_banner_points_group)
    LinearLayout points;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    private CompositeDisposable compositeSubscription;
    //默认轮播时间，10s
    private int delayTime = 10;

    private List<ImageView> imageViewList;

    private List<ArticleModel.ItemsBean> bannerList;

    //选中显示Indicator
    private int selectRes = R.drawable.shape_dots_select;

    //非选中显示Indicator
    private int unSelcetRes = R.drawable.shape_dots_default;

    //当前页的下标
    private int mCurPos;

    private boolean isStopScroll = false;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_custom_banner, this, true);
        ButterKnife.bind(this);
        imageViewList = new ArrayList<>();
    }

    /**
     * 设置轮播间隔时间
     *
     * @param time 轮播间隔时间，单位秒
     */
    public BannerView delayTime(int time) {
        this.delayTime = time;
        return this;
    }

    /**
     * 设置Points资源 Res
     *
     * @param selectRes   选中状态
     * @param unselcetRes 非选中状态
     */
    public void setPointsRes(int selectRes, int unselcetRes) {
        this.selectRes = selectRes;
        this.unSelcetRes = unselcetRes;
    }

    /**
     * 图片轮播需要传入参数
     */
    public void build(List<ArticleModel.ItemsBean> list) {
        destory();
        if (list.size() == 0) {
            this.setVisibility(GONE);
            return;
        }
        bannerList = new ArrayList<>();
        bannerList.addAll(list);
        final int pointSize;
        pointSize = bannerList.size();
        if (pointSize == 2) {
            bannerList.addAll(list);
        }
        //判断是否清空 指示器点
        if (points.getChildCount() != 0) {
            points.removeAllViewsInLayout();
        }
        //初始化与个数相同的指示器点
        for (int i = 0; i < pointSize; i++) {
            View dot = new View(getContext());
            dot.setBackgroundResource(unSelcetRes);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DisplayUtil.dp2px(getContext(), 5),
                    DisplayUtil.dp2px(getContext(), 5));
            params.leftMargin = 10;
            dot.setLayoutParams(params);
            dot.setEnabled(false);
            points.addView(dot);
        }

        points.getChildAt(0).setBackgroundResource(selectRes);

        for (int i = 0; i < bannerList.size(); i++) {
            if (i == 0) {
                mTvTitle.setText(bannerList.get(0).getTitle());
            }

            ImageView mImageView = new ImageView(getContext());

            Glide.with(getContext())
                    .load(bannerList.get(i).getThumbnail())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_pic_loading)
                    .dontAnimate()
                    .into(mImageView);
            imageViewList.add(mImageView);
        }

        //监听图片轮播，改变指示器状态
        viewPager.clearOnPageChangeListeners();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int pos) {
                pos = pos % pointSize;
                mCurPos = pos;
                for (int i = 0; i < points.getChildCount(); i++) {
                    points.getChildAt(i).setBackgroundResource(unSelcetRes);
                }
                points.getChildAt(pos).setBackgroundResource(selectRes);

                mTvTitle.setText(bannerList.get(pos).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (isStopScroll) {
                            startScroll();
                        }
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        stopScroll();
                        compositeSubscription.dispose();
                        break;
                }
            }
        });

        BannerAdapter bannerAdapter = new BannerAdapter(imageViewList);
        viewPager.setAdapter(bannerAdapter);
        bannerAdapter.notifyDataSetChanged();
        bannerAdapter.setmViewPagerOnItemClickListener(this);
        //图片开始轮播
        startScroll();
    }

    /**
     * 图片开始轮播
     */
    private void startScroll() {
        compositeSubscription = new CompositeDisposable();
        isStopScroll = false;
        Disposable disposable = Observable.timer(delayTime, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (isStopScroll) {
                            return;
                        }

                        isStopScroll = true;
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                });
        compositeSubscription.add(disposable);
    }

    /**
     * 图片停止轮播
     */
    private void stopScroll() {
        isStopScroll = true;
    }

    public void destory() {
        if (compositeSubscription != null && !compositeSubscription.isDisposed()) {
            compositeSubscription.dispose();
        }
    }
    /**
     * 设置ViewPager的Item点击回调事件
     */
    @Override
    public void onItemClick() {
        ArticleDetailActivity.start(getContext(), bannerList.get(mCurPos).getId(),
                bannerList.get(mCurPos).getUser().getNickname(),
                bannerList.get(mCurPos).getUser().getFace());
    }
}
