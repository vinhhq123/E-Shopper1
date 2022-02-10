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
    private String postAuthor;
    private String category;
    private String featured;
    private int satatusPL;
    private Date updateDate;

    public PostList() {
    }

    public PostList(int postId, String thumbnail, String postTitle, String postAuthor, String category, String featured, int satatusPL, Date updateDate) {
        this.postId = postId;
        this.thumbnail = thumbnail;
        this.postTitle = postTitle;
        this.postAuthor = postAuthor;
        this.category = category;
        this.featured = featured;
        this.satatusPL = satatusPL;
        this.updateDate = updateDate;
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

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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
    
    
}
