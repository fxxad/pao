package com.fxx.pao.model;

/**
 *  文章详情model
 * Created by fxx on 2017/8/13.
 */

public class ArticleDetailModel {


    /**
     * id : 8372
     * title : DataBinding结合AspectJ防止多次点击
     * user : {"id":5282,"face":"http://www.jcodecraeer.com/uploads/userup/5282/myface.jpeg","nickname":"ditclear"}
     * description : 写在前面 最近一直在找时间重构代码，每一次重构都能带来许多好处，比如精简代码，提高代码质量，减轻团队之间的问题，当然最重要的就是以后可以偷懒啦。而这次改进也是为了节省时间，提高团队的效率。 先体验一下效果 DataBinding 不了解的请百度，google或
     * content : 妇女节难过
     * click : 28
     * comments : 1
     * stow : 0
     * upvote : 0
     * downvote : 0
     * url : http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0812/8372.html
     * pubDate : 2017-08-12 17:02:54
     * thumbnail :
     */

    private int id;
    private String title;
    private UserBean user;
    private String description;
    private String content;
    private int click;
    private int comments;
    private int stow;
    private int upvote;
    private int downvote;
    private String url;
    private String pubDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public static class UserBean {
        /**
         * id : 5282
         * face : http://www.jcodecraeer.com/uploads/userup/5282/myface.jpeg
         * nickname : ditclear
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
