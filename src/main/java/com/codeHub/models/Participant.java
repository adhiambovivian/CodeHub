/* Copyright (C)2021  Vivian */
package com.codeHub.models;

import com.codeHub.Meta;
import java.util.Date;

public class Participant {
    private String commId;
    private Meta meta;
    private String commDomain;
    private String firstName;
    private String lastName;
    private int age;
    private String comment;
    private Date createDate;

    public Participant() {}

    public Participant(String commId, Meta meta) {
        this.commId = commId;
        this.meta = meta;
    }

    public Participant(String firstName, String lastName, int age, String commId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.commId = commId;
    }

    public String getCommId() {
        return commId;
    }

    public void setCommId(String commId) {
        this.commId = commId;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getCommDomain() {
        return commDomain;
    }

    public void setCommDomain(String commDomain) {
        this.commDomain = commDomain;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
