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
public class Setting {
    
    private int settingId;
    private int settingType;
    private String settingValue;
    private int settingOrder;
    private boolean settingStatus;
    

    public Setting() {
    }

    public Setting(int settingId, int settingType, String settingValue, int settingOrder, boolean settingStatus) {
        this.settingId = settingId;
        this.settingType = settingType;
        this.settingValue = settingValue;
        this.settingOrder = settingOrder;
        this.settingStatus = settingStatus;
    }

    public Setting(int settingId, String settingValue) {
        this.settingId = settingId;
        this.settingValue = settingValue;
    }

    public int getSettingId() {
        return settingId;
    }

    public void setSettingId(int settingId) {
        this.settingId = settingId;
    }

    public int getSettingType() {
        return settingType;
    }

    public void setSettingType(int settingType) {
        this.settingType = settingType;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public int getSettingOrder() {
        return settingOrder;
    }

    public void setSettingOrder(int settingOrder) {
        this.settingOrder = settingOrder;
    }


    public boolean isSettingStatus() {
        return settingStatus;
    }

    public void setSettingStatus(boolean settingStatus) {
        this.settingStatus = settingStatus;
    }

   

   
   
    
    
}
