package model;

import twitter4j.TweetEntity;

import java.util.Date;

/**
 * Created by Adem on 9.9.2016.
 */


public class Tweet {
    private String userName;
    private String text;
    private Date createDate;
    private int retweetCount;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "userName='" + userName + '\'' +
                ", text='" + text + '\'' +
                ", createDate=" + createDate +
                ", retweetCount=" + retweetCount +
                '}';
    }
}
