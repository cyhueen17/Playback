package com.playback;

public class ValidationParamReq {
    int userId;
    String deviceType;
    String viewableId;
    String ruleToExclude;
    boolean disableValidation;
    public ValidationParamReq() {
        userId = 0;
        ruleToExclude = "";
        disableValidation = false;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void setViewableId(String viewableId) {
        this.viewableId = viewableId;
    }

    public void setRuleToExclude(String ruleToExclude) {
        this.ruleToExclude = ruleToExclude;
    }

    public void setDisableValidation(boolean disableValidation) { this.disableValidation = disableValidation; }

    public int getUserId() {
        return userId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getViewableId() {
        return viewableId;
    }

    public String getRuleToExclude() {
        return ruleToExclude;
    }

    public boolean getDisableValidation() { return disableValidation; }
}
