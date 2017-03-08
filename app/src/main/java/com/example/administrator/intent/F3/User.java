package com.example.administrator.intent.F3;

/**
 * Created by Administrator on 2017/3/5.
 */
public class User {

    private String title;
    private String url;
    private String yes_no;

    public User(String title, String url, String yes_no) {
        this.title = title;
        this.url = url;
        this.yes_no = yes_no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getYes_no() {
        return yes_no;
    }

    public void setYes_no(String yes_no) {
        this.yes_no = yes_no;
    }

    @Override
    public String toString() {
        return "User{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", yes_no='" + yes_no + '\'' +
                '}';
    }
}
