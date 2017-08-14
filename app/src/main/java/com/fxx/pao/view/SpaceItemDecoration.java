package com.fxx.pao.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *
 * Created by Administrator on 2017/8/11 0011.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration{


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0,10,0,10);
    }
}
