package com.codeHub;

import java.util.Date;

public class SurveySendOutObject {
   private long surveyId;
   private String commDomain;
   private long creatorId;
   private long audienceId;
   private long respondentId;
   private boolean timeout;
   private String joincode;
   private long accountId;
   private Date lastUpdate;
   private String commId;
   private long timeoutValue;
   private String surveyNumber;
   private int compensation;
   private Date startDate;

    public long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(long surveyId) {
        this.surveyId = surveyId;
    }

    public String getCommDomain() {
        return commDomain;
    }

    public void setCommDomain(String commDomain) {
        this.commDomain = commDomain;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public long getAudienceId() {
        return audienceId;
    }

    public void setAudienceId(long audienceId) {
        this.audienceId = audienceId;
    }

    public long getRespondentId() {
        return respondentId;
    }

    public void setRespondentId(long respondentId) {
        this.respondentId = respondentId;
    }

    public boolean isTimeout() {
        return timeout;
    }

    public void setTimeout(boolean timeout) {
        this.timeout = timeout;
    }

    public String getJoincode() {
        return joincode;
    }

    public void setJoincode(String joincode) {
        this.joincode = joincode;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCommId() {
        return commId;
    }

    public void setCommId(String commId) {
        this.commId = commId;
    }

    public long getTimeoutValue() {
        return timeoutValue;
    }

    public void setTimeoutValue(long timeoutValue) {
        this.timeoutValue = timeoutValue;
    }

    public String getSurveyNumber() {
        return surveyNumber;
    }

    public void setSurveyNumber(String surveyNumber) {
        this.surveyNumber = surveyNumber;
    }

    public int getCompensation() {
        return compensation;
    }

    public void setCompensation(int compensation) {
        this.compensation = compensation;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
