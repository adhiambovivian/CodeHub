package com.codeHub.models;

import java.util.Date;
import java.util.List;

public class PanelS {
    public enum surveySchedule {
        IMMEDIATELY, RECURRING, LATER;
    }

    public enum gender {
        ALL, MALE, FEMALE, OTHER;
    }
    private long panelId;
    private List<String> age;
    private gender gender;
    private int compensation;
    private long target;
    private List<String> region;
    private List<Date> runTime;
    private surveySchedule scheduleType;

    public long getPanelId() {
        return panelId;
    }

    public void setPanelId(long panelId) {
        this.panelId = panelId;
    }

    public List<String> getAge() {
        return age;
    }

    public void setAge(List<String> age) {
        this.age = age;
    }

    public PanelS.gender getGender() {
        return gender;
    }

    public void setGender(PanelS.gender gender) {
        this.gender = gender;
    }

    public int getCompensation() {
        return compensation;
    }

    public void setCompensation(int compensation) {
        this.compensation = compensation;
    }

    public long getTarget() {
        return target;
    }

    public void setTarget(long target) {
        this.target = target;
    }

    public List<String> getRegion() {
        return region;
    }

    public void setRegion(List<String> region) {
        this.region = region;
    }

    public List<Date> getRunTime() {
        return runTime;
    }

    public void setRunTime(List<Date> runTime) {
        this.runTime = runTime;
    }

    public surveySchedule getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(surveySchedule scheduleType) {
        this.scheduleType = scheduleType;
    }
}
