package com.fxx.pao.event;

/**
 * 列表滑动到顶部事件类
 * Created by fxx on 2017/8/17 0017.
 */

public class ScrollToStartEvent {

    private int code;

    public ScrollToStartEvent(){}

    public ScrollToStartEvent(int code){
        this.code=code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
