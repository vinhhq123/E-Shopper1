/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author CHANHSIRO
 */
public class Slider {
    private int s_id;
    private String s_title;
    private String s_image;
    private String back_link;
    private int s_status;
    private String s_notes;

    public Slider() {
    }

    public Slider(int s_id, String s_title, String s_image, String back_link, int s_status, String s_notes) {
        this.s_id = s_id;
        this.s_title = s_title;
        this.s_image = s_image;
        this.back_link = back_link;
        this.s_status = s_status;
        this.s_notes = s_notes;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getS_title() {
        return s_title;
    }

    public void setS_title(String s_title) {
        this.s_title = s_title;
    }

    public String getS_image() {
        return s_image;
    }

    public void setS_image(String s_image) {
        this.s_image = s_image;
    }

    public String getBack_link() {
        return back_link;
    }

    public void setBack_link(String back_link) {
        this.back_link = back_link;
    }

    public int getS_status() {
        return s_status;
    }

    public void setS_status(int s_status) {
        this.s_status = s_status;
    }

    public String getS_notes() {
        return s_notes;
    }

    public void setS_notes(String s_notes) {
        this.s_notes = s_notes;
    }
    
    
    
}
