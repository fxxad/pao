package com.fxx.pao.model;

/**
 *
 * Created by fxx on 2017/8/14.
 */

public class MyProfileModel {


    /**
     * model : {"id":14161,"nickname":"fxx","face":"http://www.jcodecraeer.com/plugin/Identicon/index.php?string=fxx&size=60","sex":"男","position":"","city":"","company":"","education":"","logindate":"2017-08-14 23:31:03","signdate":"2017-08-14 08:50:08","qianming":"还木有签名哦","fans":0,"guanzhu":0}
     */

    private ModelBean model;

    public ModelBean getModel() {
        return model;
    }

    public void setModel(ModelBean model) {
        this.model = model;
    }

    public static class ModelBean {
        /**
         * id : 14161
         * nickname : fxx
         * face : http://www.jcodecraeer.com/plugin/Identicon/index.php?string=fxx&size=60
         * sex : 男
         * position :
         * city :
         * company :
         * education :
         * logindate : 2017-08-14 23:31:03
         * signdate : 2017-08-14 08:50:08
         * qianming : 还木有签名哦
         * fans : 0
         * guanzhu : 0
         */

        private int id;
        private String nickname;
        private String face;
        private String sex;
        private String position;
        private String city;
        private String company;
        private String education;
        private String logindate;
        private String signdate;
        private String qianming;
        private int fans;
        private int guanzhu;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getLogindate() {
            return logindate;
        }

        public void setLogindate(String logindate) {
            this.logindate = logindate;
        }

        public String getSigndate() {
            return signdate;
        }

        public void setSigndate(String signdate) {
            this.signdate = signdate;
        }

        public String getQianming() {
            return qianming;
        }

        public void setQianming(String qianming) {
            this.qianming = qianming;
        }

        public int getFans() {
            return fans;
        }

        public void setFans(int fans) {
            this.fans = fans;
        }

        public int getGuanzhu() {
            return guanzhu;
        }

        public void setGuanzhu(int guanzhu) {
            this.guanzhu = guanzhu;
        }
    }
}
