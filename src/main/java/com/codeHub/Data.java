/* Copyright (C)2021  Vivian */
package com.codeHub;

/** Created by vivian on 2/24/17. */
import java.sql.Date;

public class Data {
    private long id;
    private String name;
    private long country_id;
    private Boolean active;
    private long owner_account_id;
    private long owner_user_id;
    private Date create_date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(long country_id) {
        this.country_id = country_id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public long getOwner_account_id() {
        return owner_account_id;
    }

    public void setOwner_account_id(long owner_account_id) {
        this.owner_account_id = owner_account_id;
    }

    public long getOwner_user_id() {
        return owner_user_id;
    }

    public void setOwner_user_id(long owner_user_id) {
        this.owner_user_id = owner_user_id;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Data(
            long id,
            String name,
            long country_id,
            Boolean active,
            long owner_account_id,
            long owner_user_id,
            Date create_date) {
        this.id = id;
        this.name = name;
        this.country_id = country_id;
        this.active = active;
        this.owner_account_id = owner_account_id;
        this.owner_user_id = owner_user_id;
        this.create_date = create_date;
    }

    public Data() {}

    @Override
    public String toString() {
        return "Panel{"
                + "id="
                + id
                + ", name='"
                + name
                + '\''
                + ", country_id="
                + country_id
                + ", active="
                + active
                + ", owner_account_id="
                + owner_account_id
                + ", owner_user_id="
                + owner_user_id
                + ", create_date="
                + create_date
                + '}';
    }
}
