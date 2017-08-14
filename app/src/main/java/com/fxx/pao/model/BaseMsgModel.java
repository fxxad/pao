package com.fxx.pao.model;

/**
 *
 * Created by fxx on 2017/8/14 0014.
 */

public class BaseMsgModel {

    /**
     * sucess : 1
     * message : 成功登录
     * data :
     */

    private int sucess;
    private String message;
    private String data;

    public int getSucess() {
        return sucess;
    }

    public void setSucess(int sucess) {
        this.sucess = sucess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
