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
public class PostDetail {
    private int postDetailID;
    private int postId;
    private String breifInformation;
    private String postContent;
    private Date postDate;

    public PostDetail() {
    }

    public PostDetail(int postDetailID, int postId, String breifInformation, String postContent, Date postDate) {
        this.postDetailID = postDetailID;
        this.postId = postId;
        this.breifInformation = breifInformation;
        this.postContent = postContent;
        this.postDate = postDate;
    }

    public int getPostDetailID() {
        return postDetailID;
    }

    public void setPostDetailID(int postDetailID) {
        this.postDetailID = postDetailID;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
    
    
    
}
