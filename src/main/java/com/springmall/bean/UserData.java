package com.springmall.bean;

/**
 * Created by fwj on 2019-11-19.
 */
public class UserData {


    /**
     * userInfo : {"nickName":"wx","avatarUrl":""}
     * tokenExpire : 2019-11-20T23:58:27.217
     * token : ccamyt6bwelwonvgf9ckjbs5hnl1sdip
     */

    private UserInfoBean userInfo;
    private String tokenExpire;
    private String token;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public String getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(String tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class UserInfoBean {
        /**
         * nickName : wx
         * avatarUrl :
         */

        private String nickName;
        private String avatarUrl;

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }
    }
}
