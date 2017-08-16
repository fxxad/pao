package com.fxx.pao.model;

/**
 *  代码详情model
 * Created by fxx on 2017/8/11 0011.
 */

public class CodeDetailModel {

    /**
     * id : 7817
     * title : SpringActionMenu
     * describe : 一个弹性的功能菜单
     * user : {"id":1,"face":"http://www.jcodecraeer.com/uploads/userup/1/myface.png","nickname":"泡在网上的日子"}
     * thumbnail : http://jcodecraeer.oss-cn-shanghai.aliyuncs.com/image/codesample-1492048740964.gif
     * codecategory : {"catename":"菜单 (Menu)","value":5500,"count":0}
     * readme : <HTML><HEAD>  <LINK href="/templets/jcodecraeer/css/phone.css" type="text/css" rel="stylesheet"/></HEAD><body><p>详细介绍见：<a href="http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0318/7697.html" target="_self" textvalue="手把手教你绘制Android粘性果冻动画组件">手把手教你绘制Android粘性果冻动画组件</a>&nbsp;</p><h2>&nbsp;&nbsp;安装</h2><ul style="box-sizing: border-box; padding-left: 2em; margin-bottom: 16px; color: rgb(36, 41, 46); font-family: -apple-system, system-ui, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;; white-space: normal;" class=" list-paddingleft-2"><li><p>gradle</p></li><li><pre class="brush:js;toolbar:false">Step&nbsp;1.&nbsp;Add&nbsp;the&nbsp;JitPack&nbsp;repository&nbsp;to&nbsp;your&nbsp;build&nbsp;file

     allprojects&nbsp;{
     &nbsp;&nbsp;&nbsp;&nbsp;repositories&nbsp;{
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;jcenter()

     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;maven&nbsp;{&nbsp;url&nbsp;&quot;https://jitpack.io&quot;&nbsp;}
     &nbsp;&nbsp;&nbsp;&nbsp;}
     }</pre></li><li><pre>
     &nbsp;Step&nbsp;2.&nbsp;Add&nbsp;the&nbsp;dependency

     dependencies&nbsp;{
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;compile&nbsp;&#39;com.github.lilei644:SpringActionMenu:1.0.0&#39;
     }</pre></li></ul><p><span style="color: rgb(36, 41, 46); font-family: -apple-system, system-ui, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;; background-color: rgb(255, 255, 255);">xml初始化</span></p><pre class="brush:js;toolbar:false">&lt;com.lilei.springactionmenu.ActionMenu
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;android:id=&quot;@+id/actionMenu&quot;
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;android:layout_width=&quot;wrap_content&quot;
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;android:layout_height=&quot;wrap_content&quot;
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;android:layout_alignParentBottom=&quot;true&quot;
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;android:layout_alignParentRight=&quot;true&quot;
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;android:layout_marginBottom=&quot;20dp&quot;
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;app:circleRadius=&quot;25dp&quot;
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;app:dimens=&quot;10dp&quot;
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;app:animationDuration=&quot;2500&quot;
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;app:expandDirect=&quot;expandDirectTop&quot;
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;app:buttonNormalColor=&quot;#ff5bc0de&quot;
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;app:buttonPressColor=&quot;#ff39b3d7&quot;
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;app:actionMenuIcon=&quot;@drawable/add&quot;
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;app:actionMenuOnIcon=&quot;@drawable/close&quot;
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/&gt;</pre><p>add menu items &nbsp; 添加菜单组件添加菜单组件添加</p><pre class="brush:js;toolbar:false">ActionMenu&nbsp;actionMenu&nbsp;=&nbsp;(ActionMenu)&nbsp;findViewById(R.id.actionMenu);

     //&nbsp;add&nbsp;menu&nbsp;items
     actionMenu.addView(R.drawable.search,&nbsp;getItemColor(R.color.menuNormalInfo),&nbsp;getItemColor(R.color.menuPressInfo));
     actionMenu.addView(R.drawable.like,&nbsp;getItemColor(R.color.menuNormalRed),&nbsp;getItemColor(R.color.menuPressRed));
     actionMenu.addView(R.drawable.write);</pre><p>delegate &nbsp;代理监听</p><pre class="brush:js;toolbar:false">actionMenu.setItemClickListener(new&nbsp;OnActionItemClickListener()&nbsp;{
     &nbsp;&nbsp;&nbsp;&nbsp;@Override
     &nbsp;&nbsp;&nbsp;&nbsp;public&nbsp;void&nbsp;onItemClick(int&nbsp;index)&nbsp;{

     &nbsp;&nbsp;&nbsp;&nbsp;}

     &nbsp;&nbsp;&nbsp;&nbsp;@Override
     &nbsp;&nbsp;&nbsp;&nbsp;public&nbsp;void&nbsp;onAnimationEnd(boolean&nbsp;isOpen)&nbsp;{

     &nbsp;&nbsp;&nbsp;&nbsp;}
     });</pre><p>attr property &nbsp; 属性</p><table width="888"><thead style="box-sizing: border-box;"><tr style="box-sizing: border-box; background-color: rgb(255, 255, 255); border-top: 1px solid rgb(198, 203, 209);" class="firstRow"><th align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">name</th><th align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">format</th><th align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">description</th><th align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">default</th></tr></thead><tbody style="box-sizing: border-box;"><tr style="box-sizing: border-box; background-color: rgb(255, 255, 255); border-top: 1px solid rgb(198, 203, 209);"><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">circleRadius</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">dimension</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">Round menu radius</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">30</td></tr><tr style="box-sizing: border-box; background-color: rgb(246, 248, 250); border-top: 1px solid rgb(198, 203, 209);"><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">dimens</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">dimension</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">The spacing of the menu items</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">30</td></tr><tr style="box-sizing: border-box; background-color: rgb(255, 255, 255); border-top: 1px solid rgb(198, 203, 209);"><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">animationDuration</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">integer</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">Animation duration</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">500</td></tr><tr style="box-sizing: border-box; background-color: rgb(246, 248, 250); border-top: 1px solid rgb(198, 203, 209);"><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">buttonNormalColor</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">color</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">Button The color in normal condition</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">Color.RED</td></tr><tr style="box-sizing: border-box; background-color: rgb(255, 255, 255); border-top: 1px solid rgb(198, 203, 209);"><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">buttonPressColor</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">color</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">The color of the button click status</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">Color.RED</td></tr><tr style="box-sizing: border-box; background-color: rgb(246, 248, 250); border-top: 1px solid rgb(198, 203, 209);"><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">actionMenuIcon</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">reference</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">Menu icon</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">--</td></tr><tr style="box-sizing: border-box; background-color: rgb(255, 255, 255); border-top: 1px solid rgb(198, 203, 209);"><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">actionMenuOnIcon</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">reference</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">The icon when the menu is open</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">--</td></tr><tr style="box-sizing: border-box; background-color: rgb(246, 248, 250); border-top: 1px solid rgb(198, 203, 209);"><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">expandDirect</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">enum</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229);">The direction of the menu extension</td><td align="center" style="box-sizing: border-box; padding: 6px 13px; border-color: rgb(223, 226, 229); word-break: break-all;">expandDirectTop</td></tr></tbody></table><h2>Requirements &nbsp;版本要求</h2><p>Android 14+</p></body></HTML>
     * plugin :
     * comments : 0
     * stow : 20
     * size : 601 kb
     * url : https://github.com/lilei644/SpringActionMenu
     * upvote : 0
     * downvote : 0
     * click : 794
     * pubDate : 2017-04-13 09:59:10
     */

    private int id;
    private String title;
    private String describe;
    private UserBean user;
    private String thumbnail;
    private CodecategoryBean codecategory;
    private String readme;
    private String plugin;
    private int comments;
    private int stow;
    private String size;
    private String url;
    private int upvote;
    private int downvote;
    private int click;
    private String pubDate;

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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public CodecategoryBean getCodecategory() {
        return codecategory;
    }

    public void setCodecategory(CodecategoryBean codecategory) {
        this.codecategory = codecategory;
    }

    public String getReadme() {
        return readme;
    }

    public void setReadme(String readme) {
        this.readme = readme;
    }

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public static class UserBean {
        /**
         * id : 1
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

    public static class CodecategoryBean {
        /**
         * catename : 菜单 (Menu)
         * value : 5500
         * count : 0
         */

        private String catename;
        private int value;
        private int count;

        public String getCatename() {
            return catename;
        }

        public void setCatename(String catename) {
            this.catename = catename;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
