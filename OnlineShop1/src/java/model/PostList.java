/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author CHANHSIRO
 */
public class PostList {

    private int postId;
    private String thumbnail;
    private String postTitle;
    private String breifInformation;
    private String postContent;
    User postAuthor;
    Setting category;
    private int categoryId;
    private String featured;
    private int satatusPL;
    private Date date;
    private Date updateDate;
    private User user;
    private int views;
    
    public PostList() {
    }

    public PostList(int postId, String thumbnail, String postTitle, String breifInformation, String postContent, User postAuthor, Setting category, String featured, int satatusPL, Date updateDate) {
        this.postId = postId;
        this.thumbnail = thumbnail;
        this.postTitle = postTitle;
        this.breifInformation = breifInformation;
        this.postContent = postContent;
        this.postAuthor = postAuthor;
        this.category = category;
        this.featured = featured;
        this.satatusPL = satatusPL;
        this.updateDate = updateDate;
    }

    public PostList(Setting category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getBreifInformation() {
        return breifInformation;
    }

    public void setBreifInformation(String breifInformation) {
        this.breifInformation = breifInformation;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public User getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(User postAuthor) {
        this.postAuthor = postAuthor;
    }

    public Setting getCategory() {
        return category;
    }

    public void setCategory(Setting category) {
        this.category = category;
    }

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }

    public int getSatatusPL() {
        return satatusPL;
    }

    public void setSatatusPL(int satatusPL) {
        this.satatusPL = satatusPL;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
    
}
