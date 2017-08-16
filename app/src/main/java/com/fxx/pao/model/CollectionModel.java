package com.fxx.pao.model;

import java.util.List;

/**
 *  收藏列表model
 * Created by fxx on 2017/8/15 0015.
 */

public class CollectionModel {

    /**
     * total_count : 1
     * incomplete_results : true
     * items : [{"id":8086,"title":"《Minecraft》的画素风格在上了4K 之后变得截然不同了...","user":{"id":14161,"face":"http://www.jcodecraeer.com/uploads/userup/1/myface.png","nickname":"泡在网上的日子"},"click":0,"comments":0,"stow":0,"upvote":0,"downvote":0,"url":"http://www.jcodecraeer.com/api/article_detail.php/35148","pubDate":"222","channel":1,"thumbnail":" "}]
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
         * id : 8086
         * title : 《Minecraft》的画素风格在上了4K 之后变得截然不同了...
         * user : {"id":14161,"face":"http://www.jcodecraeer.com/uploads/userup/1/myface.png","nickname":"泡在网上的日子"}
         * click : 0
         * comments : 0
         * stow : 0
         * upvote : 0
         * downvote : 0
         * url : http://www.jcodecraeer.com/api/article_detail.php/35148
         * pubDate : 222
         * channel : 1
         * thumbnail :
         */

        private int id;
        private String title;
        private UserBean user;
        private int click;
        private int comments;
        private int stow;
        private int upvote;
        private int downvote;
        private String url;
        private String pubDate;
        private int channel;
        private String thumbnail;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public int getClick() {
            return click;
        }

        public void setClick(int click) {
            this.click = click;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public int getStow() {
            return stow;
        }

        public void setStow(int stow) {
            this.stow = stow;
        }

        public int getUpvote() {
            return upvote;
        }

        public void setUpvote(int upvote) {
            this.upvote = upvote;
        }

        public int getDownvote() {
            return downvote;
        }

        public void setDownvote(int downvote) {
            this.downvote = downvote;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public int getChannel() {
            return channel;
        }

        public void setChannel(int channel) {
            this.channel = channel;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public static class UserBean {
            /**
             * id : 14161
             * face : http://www.jcodecraeer.com/uploads/userup/1/myface.png
             * nickname : 泡在网上的日子
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
