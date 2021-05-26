package com.codeHub.models;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by vivian on 2/24/17.
 */
public class Person implements Serializable{
    private long id;
    //private Participant participant;
    private long accountId;
    private long blockedBy;
    private long countryId;
    private Date createDate;
    private Date expirationDate;
    private transient String comment;
    private String mode;
    private long surveyId;
    private String commId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
//
//    public Participant getParticipant() {
//        return participant;
//    }
//
//    public void setParticipant(Participant participant) {
//        this.participant = participant;
//    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getBlockedBy() {
        return blockedBy;
    }

    public void setBlockedBy(long blockedBy) {
        this.blockedBy = blockedBy;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(long surveyId) {
        this.surveyId = surveyId;
    }

    public String getCommId() {
        return commId;
    }

    public void setCommId(String commId) {
        this.commId = commId;
    }

    public Person(String comment, String mode, long surveyId, String commId) {
        this.comment = comment;
        this.mode = mode;
        this.surveyId = surveyId;
        this.commId = commId;
    }

    public Person(String comment, String commId) {
        this.comment = comment;
        this.commId = commId;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
//                ", participant=" + participant +
                ", accountId=" + accountId +
                ", blockedBy=" + blockedBy +
                ", countryId=" + countryId +
                ", commId="+commId+
                ", createDate=" + createDate +
                ", expirationDate=" + expirationDate +
                ", comment='" + comment + '\'' +
                ", mode='" + mode + '\'' +
                ", surveyId=" + surveyId +
                '}';
    }
}

