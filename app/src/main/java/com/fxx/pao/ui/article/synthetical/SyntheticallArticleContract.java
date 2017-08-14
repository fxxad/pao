package com.fxx.pao.ui.article.synthetical;

import com.fxx.pao.base.BasePresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.ArticleModel;

import java.util.List;

/**
 *
 * Created by fxx on 2017/8/11 0011.
 */

public interface SyntheticallArticleContract {
    interface View extends BaseView{
        void refreshArticles(List<ArticleModel.ItemsBean> itemsBeen);
        void appendArticles(List<ArticleModel.ItemsBean> itemsBeen);
    }
    interface Presenter extends BasePresenter<View>{
        void loadInitArticles();
        void loadMoreArticles();
    }
}
