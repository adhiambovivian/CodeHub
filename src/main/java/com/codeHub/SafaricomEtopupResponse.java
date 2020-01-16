package com.codeHub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="COMMAND",namespace = "http://www.tibco.com/schemas/pinless/PINLESS.core/C2STransferBillPayment/Schema.xsd9")
@XmlAccessorType(XmlAccessType.FIELD)
public class SafaricomEtopupResponse {

@XmlElement(name="TYPE")
private String type;

@XmlElement(name="TXNSTATUS")
private String txnstatus;

@XmlElement(name="DATE")
private String date;

@XmlElement(name="EXTREFNUM")
private String extrefnum;

@XmlElement(name="TXNID")
private String txnid;

@XmlElement(name="MESSAGE")
private String message;

private String other;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTxnstatus() {
        return txnstatus;
    }

    public void setTxnstatus(String txnstatus) {
        this.txnstatus = txnstatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExtrefnum() {
        return extrefnum;
    }

    public void setExtrefnum(String extrefnum) {
        this.extrefnum = extrefnum;
    }

    public String getTxnid() {
        return txnid;
    }

    public void setTxnid(String txnid) {
        this.txnid = txnid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
