package com.example.anchala.seo_project;

public class Phone {

    private String home;
    private String mobile;

    public Phone(String mobile , String home) {
        this.mobile = mobile;
        this.home = home;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
