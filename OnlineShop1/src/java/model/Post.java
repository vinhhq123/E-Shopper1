/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author hungn
 */
public class Post {
    private int postId;
    private String thumbnail;
    private String postTitle;
    private String postAuthor;
    private String postCategory;
    private String featured;
    private Date updateDate;

    public Post() {
    }

    public Post(int postId, String thumbnail, String postTitle, String postAuthor, String postCategory, String featured, Date updateDate) {
        this.postId = postId;
        this.thumbnail = thumbnail;
        this.postTitle = postTitle;
        this.postAuthor = postAuthor;
        this.postCategory = postCategory;
        this.featured = featured;
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

    public String getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(String postCategory) {
        this.postCategory = postCategory;
    }

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    
}
