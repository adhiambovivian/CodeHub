package com.codeHub;

import java.math.BigDecimal;
import java.util.Date;

public class IncentiveAccount {
    public enum incentiveAccountType{
        LINE,ETOPUP;
    }
    private long id;
    private long countryId;
    private String name;
    private BigDecimal balance;
    private boolean active;
    private Date createDate;
    private String type;
    private incentiveAccountType accountType;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public incentiveAccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(incentiveAccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "IncentiveAccount{" +
                "id=" + id +
                ", countryId=" + countryId +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", active=" + active +
                ", createDate=" + createDate +
                ", type='" + type + '\'' +
                '}';
    }

}
