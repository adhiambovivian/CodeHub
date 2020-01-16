package com.codeHub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@XmlRootElement(name = "COMMAND",namespace="http://safaricom.co.ke/Pinless/keyaccounts/")
@XmlAccessorType(XmlAccessType.FIELD)
public class SafaricomEtopup {

    @XmlElement(name = "TYPE",namespace="http://safaricom.co.ke/Pinless/keyaccounts/")
    private String type = "EXRCTRFREQ";

    @XmlElement(name = "DATE",namespace="http://safaricom.co.ke/Pinless/keyaccounts/")
    private String date = formatDate();

    @XmlElement(name = "EXTNWCODE",namespace="http://safaricom.co.ke/Pinless/keyaccounts/")
    private String extnwcode = "SA";

    @XmlElement(name = "MSISDN",namespace="http://safaricom.co.ke/Pinless/keyaccounts/")
    private String msisdn = "700945667";

    @XmlElement(name = "PIN",namespace="http://safaricom.co.ke/Pinless/keyaccounts/")
    private String pin="5237";

    @XmlElement(name = "LOGINID",namespace="http://safaricom.co.ke/Pinless/keyaccounts/")
    private String loginid="";

    @XmlElement(name = "PASSWORD",namespace="http://safaricom.co.ke/Pinless/keyaccounts/")
    private String password="";

    @XmlElement(name = "EXTCODE",namespace="http://safaricom.co.ke/Pinless/keyaccounts/")
    private String extcode = "D-M172";

    @XmlElement(name = "EXTREFNUM",namespace="http://safaricom.co.ke/Pinless/keyaccounts/")
    private String extrefnum = "1234";

    @XmlElement(name = "MSISDN2",namespace="http://safaricom.co.ke/Pinless/keyaccounts/")
    private String msisdn2;

    @XmlElement(name = "AMOUNT",namespace="http://safaricom.co.ke/Pinless/keyaccounts/")
    private String amount;

    @XmlElement(name = "LANGUAGE1",namespace="http://safaricom.co.ke/Pinless/keyaccounts/")
    private String language1="0";

    @XmlElement(name = "LANGUAGE2",namespace="http://safaricom.co.ke/Pinless/keyaccounts/")
    private String language2="0";

    @XmlElement(name = "SELECTOR",namespace="http://safaricom.co.ke/Pinless/keyaccounts/")
    private String selector="1";

    private String other;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExtnwcode() {
        return extnwcode;
    }

    public void setExtnwcode(String extnwcode) {
        this.extnwcode = extnwcode;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExtcode() {
        return extcode;
    }

    public void setExtcode(String extcode) {
        this.extcode = extcode;
    }

    public String getExtrefnum() {
        return extrefnum;
    }

    public void setExtrefnum(String extrefnum) {
        this.extrefnum = extrefnum;
    }

    public String getMsisdn2() {
        return msisdn2;
    }

    public void setMsisdn2(String msisdn2) {
        this.msisdn2 = msisdn2;
    }

    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLanguage1() {
        return language1;
    }

    public void setLanguage1(String language1) {
        this.language1 = language1;
    }

    public String getLanguage2() {
        return language2;
    }

    public void setLanguage2(String language2) {
        this.language2 = language2;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector1(String selector) {
        this.selector = selector;
    }

    public String formatDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d H:m:s");
        String formattedString = LocalDateTime.now().format(formatter);
        return formattedString;
    }
}


