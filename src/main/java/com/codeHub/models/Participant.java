package com.codeHub.models;

import com.codeHub.Meta;

public class Participant {
    private String commId;
    private Meta meta;
    private String commDomain;

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

    public Participant(String commId, Meta meta) {
        this.commId = commId;
        this.meta = meta;
    }

    public String getCommDomain() {
        return commDomain;
    }

    public void setCommDomain(String commDomain) {
        this.commDomain = commDomain;
    }

    public Participant() {
    }
}
