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
    private String settingOrder;
    private boolean settingStatus;

    public Setting() {
    }

    public Setting(int settingId, int settingType, String settingValue, String settingOrder, boolean settingStatus) {
        this.settingId = settingId;
        this.settingType = settingType;
        this.settingValue = settingValue;
        this.settingOrder = settingOrder;
        this.settingStatus = settingStatus;
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

    public String getSettingOrder() {
        return settingOrder;
    }

    public void setSettingOrder(String settingOrder) {
        this.settingOrder = settingOrder;
    }

    public boolean isSettingStatus() {
        return settingStatus;
    }

    public void setSettingStatus(boolean settingStatus) {
        this.settingStatus = settingStatus;
    }
    
    
}
