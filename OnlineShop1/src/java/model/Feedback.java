/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author HL2020
 */
public class Feedback {
    private int feedbackId;
    private int customerId;
    private int ratedStart;
    private int productID;
    private String image;
    private int feedbackStatus;
    private String feedbackContent;
    private Date updatedDate;
    private String title;
    private String note;

    public Feedback() {
    }

    public Feedback(int feedbackId, int customerId, int ratedStart, int productID, String image, int feedbackStatus, String feedbackContent, Date updatedDate, String title, String note) {
        this.feedbackId = feedbackId;
        this.customerId = customerId;
        this.ratedStart = ratedStart;
        this.productID = productID;
        this.image = image;
        this.feedbackStatus = feedbackStatus;
        this.feedbackContent = feedbackContent;
        this.updatedDate = updatedDate;
        this.title = title;
        this.note = note;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRatedStart() {
        return ratedStart;
    }

    public void setRatedStart(int ratedStart) {
        this.ratedStart = ratedStart;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(int feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
}
