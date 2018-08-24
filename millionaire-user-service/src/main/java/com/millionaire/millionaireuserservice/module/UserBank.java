package com.millionaire.millionaireuserservice.module;

public class UserBank {
    private Long id;

    private String city;

    private String bankName;

    private String cardNumber;

    private String cardType;

    private String bankPhone;

    private Long uid;

    private Long gmtCreate;

    private Long gmtUpdate;

    public UserBank(Long id, String city, String bankName, String cardNumber, String cardType, String bankPhone, Long uid, Long gmtCreate, Long gmtUpdate) {
        this.id = id;
        this.city = city;
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.bankPhone = bankPhone;
        this.uid = uid;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }

    public UserBank() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getBankPhone() {
        return bankPhone;
    }

    public void setBankPhone(String bankPhone) {
        this.bankPhone = bankPhone;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    @Override
    public String toString() {
        return "UserBank{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", bankName='" + bankName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardType='" + cardType + '\'' +
                ", bankPhone='" + bankPhone + '\'' +
                ", uid=" + uid +
                ", gmtCreate=" + gmtCreate +
                ", gmtUpdate=" + gmtUpdate +
                '}';
    }
}