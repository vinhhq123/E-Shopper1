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
public class SettingType {
    
    private int settingTypeId;
    private String typeName;

    public SettingType() {
    }

    public SettingType(int settingTypeId, String typeName) {
        this.settingTypeId = settingTypeId;
        this.typeName = typeName;
    }

    public int getSettingTypeId() {
        return settingTypeId;
    }

    public void setSettingTypeId(int settingTypeId) {
        this.settingTypeId = settingTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    
    
}
