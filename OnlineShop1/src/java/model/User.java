/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author VINH
 */
public class User {
    
    private int uid;
    private String email;
    private String fullname;
    private String password;
    private String title;
    private boolean gender;
    private String phone;
    private String address;
    private String avatar;
    private int accountStatus;
    private int role;

    public User() {
    }

    public User(int uid, String email, String fullname, String password, String title, boolean gender, String phone, String address, String avatar, int accountStatus, int role) {
        this.uid = uid;
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.title = title;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.avatar = avatar;
        this.accountStatus = accountStatus;
        this.role = role;
    }

   

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
}
