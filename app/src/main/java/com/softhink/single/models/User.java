package com.softhink.single.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("order")
    private int order;
    @SerializedName("token")
    private String token;
    @SerializedName("imageProfile")
    private String imageProfile;
    @SerializedName("email")
    private String email;
    @SerializedName("profileType")
    private int profileType;
    @SerializedName("fullname")
    private String fullname;
    @SerializedName("userId")
    private int userId;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getProfileType() {
        return profileType;
    }

    public void setProfileType(int profileType) {
        this.profileType = profileType;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
