package com.fxx.pao.model;

import java.util.List;

/**
 *
 * Created by fxx on 2017/8/14 0014.
 */

public class CommentModel {


    /**
     * total_count : 1
     * incomplete_results : true
     * items : [{"id":6646,"msg":"github上面少了一个架包啊","user":{"id":13952,"face":"http://www.jcodecraeer.com/plugin/Identicon/index.php?string=yyyyh&size=40","nickname":"yyyyh"},"dtime":"5天以前"}]
     */

    private int total_count;
    private boolean incomplete_results;
    private List<ItemsBean> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * id : 6646
         * msg : github上面少了一个架包啊
         * user : {"id":13952,"face":"http://www.jcodecraeer.com/plugin/Identicon/index.php?string=yyyyh&size=40","nickname":"yyyyh"}
         * dtime : 5天以前
         */

        private int id;
        private String msg;
        private UserBean user;
        private String dtime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getDtime() {
            return dtime;
        }

        public void setDtime(String dtime) {
            this.dtime = dtime;
        }

        public static class UserBean {
            /**
             * id : 13952
             * face : http://www.jcodecraeer.com/plugin/Identicon/index.php?string=yyyyh&size=40
             * nickname : yyyyh
             */

            private int id;
            private String face;
            private String nickname;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
    }
}
